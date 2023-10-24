package com.jnu.wifi6.controller

import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.kotlin.InfluxDBClientKotlinFactory
import com.jnu.wifi6.domain.Mem
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/api/v1")
class WifiSyncController(
//    val clientService: ClientService,
) {
    val log = mu.KotlinLogging.logger {}

    @GetMapping("/networks/{network_id}/clients")
    fun getNetworkClients(@PathVariable("network_id") networkId: String) {
//        clientService.getUserInformation(networkId)
    }
}
