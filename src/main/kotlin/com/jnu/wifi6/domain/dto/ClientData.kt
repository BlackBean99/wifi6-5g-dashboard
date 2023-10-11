package com.jnu.wifi6.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(SnakeCaseStrategy::class)
data class ClientData(
    val id: String?,
    val mac: String?,
    val description: String?,
    val ip: String?,
    val user: String?,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    val firstSeen: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    val lastSeen: LocalDateTime?,
    val deviceTypePrediction: String?,
    val recentDeviceSerial: String?,
    val recentDeviceName: String?,
    val recentDeviceMac: String?,
    val recentDeviceConnection: String?,
    val ssid: String?,
    val status: String?,
    val usageSent: Long,
    val usageRecv: Long,
    val usageTotal: Long,

    /**
     eduroam 접속자
     @sample
     {
     "id": "k0add8f",
     "mac": "7e:c3:05:88:19:ce",
     "description": null,
     "ip": "10.10.221.143",
     "ip6": null,
     "ip6Local": "x:x:x:x:x:x:x:x",
     "user": "000000@jnu.ac.kr",
     "firstSeen": "2023-09-26T01:56:04Z",
     "lastSeen": "2023-10-08T10:05:31Z",
     "manufacturer": null,
     "os": null,
     "deviceTypePrediction": "iPhone 11, iOS17.0.2",
     "recentDeviceSerial": "xxx",
     "recentDeviceName": "xxx",
     "recentDeviceMac": "xxxxx",
     "recentDeviceConnection": "Wireless",
     "ssid": "JNU",
     "vlan": "",
     "switchport": null,
     "usage": {
     "sent": 74,
     "recv": 26,
     "total": 100
     },
     "status": "Offline",
     "notes": null,
     "groupPolicy8021x": null,
     "adaptivePolicyGroup": null,
     "smInstalled": false,
     "namedVlan": null,
     "pskGroup": null
     },
     non login 접속자
     *   "id": "k0a7685",
     "mac": "ae:75:27:c6:29:e5",
     "description": "Han",
     "ip": "10.10.118.133",
     "ip6": null,
     "ip6Local": null,
     "user": null,
     "firstSeen": "2023-10-08T05:12:04Z",
     "lastSeen": "2023-10-08T05:12:04Z",
     "manufacturer": null,
     "os": null,
     "deviceTypePrediction": null,
     "recentDeviceConnection": "Wireless",
     "recentDeviceSerial": "xxx",
     "recentDeviceName": "xxx",
     "recentDeviceMac": "xxxxx",
     "ssid": "JNU",
     "vlan": "",
     "switchport": null,
     "usage": {
     "sent": 2,
     "recv": 1,
     "total": 3
     },
     "status": "Offline",
     "notes": null,
     "groupPolicy8021x": null,
     "adaptivePolicyGroup": null,
     "smInstalled": false,
     "namedVlan": null,
     "pskGroup": null
     * */
)
