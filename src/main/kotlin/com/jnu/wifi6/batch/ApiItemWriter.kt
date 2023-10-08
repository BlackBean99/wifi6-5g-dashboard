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

@Component
class ApiItemWriter(

)  : ItemWriter<List<ClientData>> {
    override fun write(items: MutableList<out List<ClientData>>) {
        // items에 읽어온 데이터가 들어있습니다.

        // ClientData를 InfluxDB에 비동기적으로 쓰는 로직을 구현
        val token = "a6e20dcb16b7d374169f1c8fdb53fc2375df6d94"
        val org = "JNU"
        val bucket = "JNU"

        // InfluxDB Kotlin 클라이언트를 생성
        val client = InfluxDBClientKotlinFactory.create("http://aimon.jnu.ac.kr:8086", token!!.toCharArray(), org, bucket)

        runBlocking {
            val writeApi = client.getWriteKotlinApi()

            // 코루틴을 사용하여 비동기로 데이터 쓰기 작업을 수행
            items.forEach { clientDataList ->
                launch(Dispatchers.IO) {
                    clientDataList.forEach { clientData ->
                        val lastSeenTime = clientData.lastSeen// lastSeen 속성을 InfluxDB의 시간 형식으로 변환
                        val point = Point.measurement("measurementName")
                            /*
                                val id: String,
                                val mac: String,
                                val description: String?,
                                val ip: String,
                                val user: String?,
                                val firstSeen: String,
                                val lastSeen: String,
                                val deviceTypePrediction: String?,
                                val recentDeviceSerial: String,
                                val recentDeviceName: String,
                                val recentDeviceMac: String,
                                val recentDeviceConnection: String,
                                val ssid: String,
                                val status: String,
                                val usageSent: Long,
                                val usageRecv: Long,
                                val usageTotal: Long,
                             */
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
                            /*
                             .addTag("vlan", clientData.vlan)
                             .addTag("switchport", clientData.switchport)
                             .addTag("notes", clientData.notes)
                             .addTag("groupPolicy8021x", clientData.groupPolicy8021x)
                             .addTag("adaptivePolicyGroup", clientData.adaptivePolicyGroup)
                             .addTag("smInstalled", clientData.smInstalled.toString())
                             .addTag("namedVlan", clientData.namedVlan)
                             .addTag("pskGroup", clientData.pskGroup)
                             .addTag("manufacturer", clientData.manufacturer)
                             .addTag("os", clientData.os)
                             .addTag("ip6", clientData.ip6)
                             .addTag("ip6Local", clientData.ip6Local)
                             */
                            .addTag("ssid", clientData.ssid)
                            .addTag("status", clientData.status)
                            .addTag("firstSeen", clientData.firstSeen)
                            .addTag("lastSeen", clientData.lastSeen)
                            .addTag("usageSent", clientData.usageSent.toString())
                            .addTag("usageRecv", clientData.usageRecv.toString())
                            .addTag("usageTotal", clientData.usageTotal.toString())
                            .time(Instant.parse(lastSeenTime), WritePrecision.NS)
                        // 데이터를 비동기적으로 InfluxDB에 쓰기
                        writeApi.writePoint(point)
                    }
                }
            }
            // 모든 비동기 작업이 완료될 때까지 대기
        }
        // 클라이언트를 닫습니다.
        client.close()
    }
}





