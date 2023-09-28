package com.jnu.wifi6.helper

import com.depromeet.whatnow.annotation.Helper
import com.jnu.wifi6.client.MerakiClient
import com.jnu.wifi6.config.MerakiProperties
import com.jnu.wifi6.domain.dto.ClientData
import com.jnu.wifi6.domain.dto.NetworkInfo

@Helper
class MerakiHelper(
    val merakiProperties: MerakiProperties,
    val merakiClient: MerakiClient,
) {

    fun getNetworkIds(): List<NetworkInfo> {
//        return merakiClient.getNetworkIds();
        return listOf();
    }
    fun getNetworkInfo(): List<ClientData> {
//        return merakiClient.getN();
        return listOf();
    }
}