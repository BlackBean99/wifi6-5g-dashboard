package com.jnu.wifi6.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
