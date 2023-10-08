package com.jnu.wifi6.batch

import com.jnu.wifi6.client.MerakiClient
import com.jnu.wifi6.config.MerakiProperties
import com.jnu.wifi6.domain.Clients
import com.jnu.wifi6.domain.dto.ClientData
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component


@Component
class ApiItemReader(
    val merakiProperties: MerakiProperties,
    val merakiClient: MerakiClient
) : ItemReader<List<ClientData>> {

    // API 요청 및 데이터 읽기 로직을 여기에 구현

    override fun read(): List<ClientData> {
        // API를 호출하고 데이터를 읽어오는 로직
        val data = fetchDataFromApi()

        // 데이터를 더 이상 읽을 수 없을 때는 null 반환
        return data
    }

    private fun fetchDataFromApi(): List<ClientData> {
        // API 요청 및 데이터 읽기 로직을 구현
        // 예를 들어, RestTemplate 또는 WebClient를 사용하여 API 호출
        // 읽어온 데이터를 YourDataClass 형태로 반환
//        val networkIds = merakiClient.getNetworkIds()
        return merakiClient.getNetworkInfo("L_646829496481105433", merakiProperties.meraki.secret)
    }
}