package com.jnu.wifi6.batch

import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.kotlin.InfluxDBClientKotlinFactory
import com.influxdb.client.write.Point
import com.jnu.wifi6.domain.dto.ClientData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.batch.item.ItemWriter
import java.time.Instant
import java.time.LocalDateTime

@Component
class ApiItemWriter(

)  : ItemWriter<List<ClientData>> {
    override fun write(items: MutableList<out List<ClientData>>) {
        val logger = mu.KotlinLogging.logger {}
        // items에 읽어온 데이터가 들어있습니다.

        // ClientData를 InfluxDB에 비동기적으로 쓰는 로직을 구현
        val token = "F5_a5VCwGQwClcQigBrqd25fQ3b3i3xK_8k5J7Cjv4XKVVVxZ58BB2xQtb85wqoSa9Q0Cdzyqy5Co3TSARKbzQ=="
        val org = "JNU"
        val bucket = "JNU"

        // InfluxDB Kotlin 클라이언트를 생성
        val client = InfluxDBClientKotlinFactory.create("http://aimon.jnu.ac.kr:8086", token!!.toCharArray(), org, bucket)

        val clientDataList = items.flatten().distinctBy { it.id }
        runBlocking {
            val writeApi = client.getWriteKotlinApi()
//            val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'")

            clientDataList.forEach { clientData ->
                launch(Dispatchers.IO) {
                    val point = Point
                        .measurement("device_data")
                        .addTag("id", clientData.id)
                        .addTag("mac", clientData.mac)
                        .addTag("description", clientData.description)
                        .addTag("ip", clientData.ip)
                        .addTag("user", clientData.user)
                        .addTag("deviceTypePrediction", clientData.deviceTypePrediction)
                        .addTag("recentDeviceSerial", clientData.recentDeviceSerial)
                        .addTag("recentDeviceName", clientData.recentDeviceName)
                        .addTag("recentDeviceMac", clientData.recentDeviceMac)
                        .addTag("recentDeviceConnection", clientData.recentDeviceConnection)
                        .addTag("ssid", clientData.ssid)
                        .addTag("status", clientData.status)
                        .addTag("firstSeen", clientData.firstSeen)
                        .addTag("lastSeen", clientData.lastSeen)
                        .addField("usageSent", clientData.usage?.sent)
                        .addField("usageRecv", clientData.usage?.recv)
                        .addField("usageTotal", clientData.usage?.total)
                        .time(Instant.parse(clientData.lastSeen ?: LocalDateTime.now().toString()), WritePrecision.MS)

                    // 데이터를 비동기적으로 InfluxDB에 쓰기
                    logger.info("save - clientData: ${clientData.id}")
                    writeApi.writePoint(point)
                }
            }
        }
            // 모든 비동기 작업이 완료될 때까지 대기
        // 클라이언트를 닫습니다.
        client.close()
    }
}