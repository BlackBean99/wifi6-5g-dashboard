package com.jnu.wifi6.batch

import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.kotlin.InfluxDBClientKotlinFactory
import com.influxdb.client.write.Point
import com.jnu.wifi6.config.influx.InfluxProperties
import com.jnu.wifi6.domain.dto.ClientData
import kotlinx.coroutines.runBlocking
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime

@Component
class CountItemWriter(
    val influxProperties: InfluxProperties,
    val influxReader: InfluxReader,
) : ItemWriter<List<ClientData>> {
    override fun write(items: MutableList<out List<ClientData>>) {
        val logger = mu.KotlinLogging.logger {}
        // items에 읽어온 데이터가 들어있습니다.

        // ClientData를 InfluxDB에 비동기적으로 쓰는 로직을 구현
        // InfluxDB Kotlin 클라이언트를 생성

        val client = InfluxDBClientKotlinFactory.create(
            "http://" + influxProperties.properties.url,
            influxProperties.properties.token.toCharArray(),
            influxProperties.properties.org,
            influxProperties.properties.bucket,
        )

        val clientDataList = items.flatten().distinctBy { it.id }
        runBlocking {
            val writeApi = client.getWriteKotlinApi()

            val isDuplicated = influxReader.isDuplicated(clientDataList.first().id!!)
//            인증/비인증  사용자의  사용량과  시간을  넣어주세요
//            일별로  데이터를  넣어주세요

            val totalCount = clientDataList.size
            val authenticationCount = clientDataList.filter { it.user != null }.size
            val nonAuthenticationCount = clientDataList.filter { it.user == null }.size
            val authenticationUsage = clientDataList.filter { it.user != null }.map { it.usage?.total ?: 0 }.sum()
            val nonAuthenticationUsage = clientDataList.filter { it.user == null }.map { it.usage?.total ?: 0 }.sum()
            val totalUsage = clientDataList.map { it.usage?.total ?: 0 }.sum()

            val onlineCount = clientDataList.filter { it.status == "Online" }.size
            val offlineCount = clientDataList.filter { it.status == "Offline" }.size
            val wirelessCount = clientDataList.filter { it.recentDeviceConnection == "Wireless" }.size
            val wiredCount = clientDataList.filter { it.recentDeviceConnection == "Wired" }.size

            val point = Point
                .measurement("count_statistics")
                .addTag("id", clientDataList.first().id)
                .addField("authenticationCount", authenticationCount)
                .addField("nonAuthenticationCount", nonAuthenticationCount)
                .addField("total", totalCount)
                .addField("authenticationUsage", authenticationUsage)
                .addField("nonAuthenticationUsage", nonAuthenticationUsage)
                .addField("usage", totalUsage)
                .addField("onlineCount", onlineCount)
                .addField("offlineCount", offlineCount)
                .addField("wirelessCount", wirelessCount)
                .addField("wiredCount", wiredCount)
                .time(Instant.parse(clientDataList.first().lastSeen ?: LocalDateTime.now().toString()), WritePrecision.MS)
            // pass
            if (isDuplicated) {
                writeApi.writePoint(point)
                logger.info(
                    "batch id: ${clientDataList.first().id}, totalCount: $totalCount, authenticationCount: $authenticationCount, nonAuthenticationCount: $nonAuthenticationCount, usage: $totalUsage \n" +
                        "authenticationUsage: $authenticationUsage, nonAuthenticationUsage: $nonAuthenticationUsage",
                )
            }
            // 모든 비동기 작업이 완료될 때까지 대기
            // 클라이언트를 닫습니다.
            client.close()
        }
    }
}
