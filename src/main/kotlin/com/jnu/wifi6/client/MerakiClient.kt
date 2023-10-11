package com.jnu.wifi6.client

import com.jnu.wifi6.config.meraki.MerakiConfig
import com.jnu.wifi6.domain.dto.ClientData
import com.jnu.wifi6.domain.dto.NetworkInfo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "MerakiClient", url = "\${properties.meraki.url}", configuration = [MerakiConfig::class])
interface MerakiClient {
    /**
     * @param networkId : 네트워크 ID
     * @param token : API 토큰
     * @param contentType : application/json
     * @suppress https://developer.cisco.com/meraki/api/get-network-clients/
     * @throws 403 : API 토큰이 잘못되었거나, 네트워크 ID가 잘못되었을 경우
     * @return List<ClientData> : 네트워크에 연결된 클라이언트 정보
     */
    @GetMapping("/networks/{network_id}/clients")
    fun getNetworkInfo(
        @PathVariable(name = "network_id") networkId: String,
        @RequestHeader("Authorization") token: String,
        @RequestHeader("Accept") contentType: String = "application/json",
    ): List<ClientData>

    /**
     * @param token : API 토큰
     * @param contentType : application/json
     * @suppress https://developer.cisco.com/meraki/api/get-organizations/
     * @throws 403 : API 토큰이 잘못되었을 경우
     * */
    @GetMapping("/networks/{network_id}")
    fun getNetworkIds(
        @PathVariable(name = "network_id") networkId: String,
        @RequestHeader("Authorization") token: String,
        @RequestHeader("Accept") contentType: String = "application/json",
    ): List<NetworkInfo>
}
