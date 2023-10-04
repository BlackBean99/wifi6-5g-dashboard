//package com.jnu.wifi6.client
//
//import com.jnu.wifi6.config.meraki.MerakiConfig
//import com.jnu.wifi6.domain.dto.ClientData
//import com.jnu.wifi6.domain.dto.NetworkInfo
//import org.springframework.cloud.openfeign.FeignClient
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestHeader
//
//@FeignClient(name = "MerakiClient", url = "\${meraki.url}", configuration = [MerakiConfig::class])
//interface MerakiClient {
//    @GetMapping("/networks/{network_id}/clients")
//    fun getNetworkInfo(
//        @PathVariable(name = "network_id") networkId: String,
//        @RequestHeader("Authorization") token: String,
//    ): List<ClientData>
//
//    @GetMapping("/networks/{network_id}")
//    fun getNetworkIds(
//        @PathVariable(name = "network_id") networkId: String,
//        @RequestHeader("Authorization") token: String,
//    ): List<NetworkInfo>
//}
