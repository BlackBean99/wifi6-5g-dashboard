package com.jnu.wifi6.controller

import com.influxdb.client.domain.WritePrecision
import com.influxdb.client.kotlin.InfluxDBClientKotlinFactory
import com.jnu.wifi6.domain.Mem
//import com.jnu.wifi6.service.ClientService
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

    @GetMapping("")
    suspend fun testClient() {
        // You can generate an API token from the "API Tokens Tab" in the UI
        val token = "a6e20dcb16b7d374169f1c8fdb53fc2375df6d94"
        val org = "JNU"
        val bucket = "JNU"


        val client = InfluxDBClientKotlinFactory.create("http://aimon.jnu.ac.kr:8086", token!!.toCharArray(), org, bucket)
        val writeApi = client.getWriteKotlinApi()

//        val record = "mem,host=host1 used_percent=23.43234543"

        val mem = Mem("host1", 23.43234543, Instant.now())
        log.debug("testClient")
        writeApi.writeMeasurement(mem, WritePrecision.NS)
    }
}
