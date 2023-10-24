package com.jnu.wifi6.batch

import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.kotlin.InfluxDBClientKotlinFactory
import com.influxdb.client.write.Point
import com.jnu.wifi6.config.influx.InfluxProperties
import com.jnu.wifi6.domain.dto.ClientData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.batch.item.ItemWriter
import java.time.Instant
import java.time.LocalDateTime

@Component
class CountItemWriter(
    val influxProperties: InfluxProperties,
)  : ItemWriter<List<ClientData>> {
    override fun write(items: MutableList<out List<ClientData>>) {
        val logger = mu.KotlinLogging.logger {}
        // items에 읽어온 데이터가 들어있습니다.

        // ClientData를 InfluxDB에 비동기적으로 쓰는 로직을 구현
        // InfluxDB Kotlin 클라이언트를 생성

        val client = InfluxDBClientKotlinFactory.create(
            "http://" + influxProperties.properties.url,
            influxProperties.properties.token.toCharArray(),
            influxProperties.properties.org,
            influxProperties.properties.bucket
        )

        val clientDataList = items.flatten().distinctBy { it.id }
        runBlocking {
            val writeApi = client.getWriteKotlinApi()

            val totalCount = clientDataList.size
            val authenticationCount = clientDataList.filter { it.user != null }.size
            val nonAuthenticationCount = clientDataList.filter { it.user == null }.size


            val point = Point
                .measurement("connection_count")
                .addTag("id", clientDataList.first().id)
                .addField("totlaCount", authenticationCount)
                .addField("authenticationCount", authenticationCount)
                .addField("nonAuthenticationCount", nonAuthenticationCount)
                .addField("total", totalCount)
                .time(Instant.parse(clientDataList.first().lastSeen ?: LocalDateTime.now().toString()), WritePrecision.MS)
            writeApi.writePoint(point)
                // 모든 비동기 작업이 완료될 때까지 대기
            // 클라이언트를 닫습니다.
            client.close()
        }
    }
}