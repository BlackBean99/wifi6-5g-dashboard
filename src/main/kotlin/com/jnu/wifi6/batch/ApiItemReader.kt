package com.jnu.wifi6.batch

import com.jnu.wifi6.client.MerakiClient
import com.jnu.wifi6.config.MerakiProperties
import com.jnu.wifi6.domain.dto.ClientData
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class ApiItemReader() : ItemReader<List<ClientData>> {
    @Autowired
    private lateinit var merakiProperties: MerakiProperties

    @Autowired
    private lateinit var merakiClient: MerakiClient

    // API 요청 및 데이터 읽기 로직을 여기에 구현
    // logger
    private val logger = LoggerFactory.getLogger(this.javaClass.simpleName)

    override fun read(): List<ClientData> {
        // API를 호출하고 데이터를 읽어오는 로직
        val data = fetchDataFromApi()
        // 데이터를 더 이상 읽을 수 없을 때는 null 반환
        return data
    }

    private fun fetchDataFromApi(): List<ClientData> {
        try {
            // API 요청 및 데이터 읽기 로직을 구현
            // 예를 들어, RestTemplate 또는 WebClient를 사용하여 API 호출
            val token = "Bearer " + merakiProperties.meraki.secret
            val clientDataList = merakiClient.getNetworkInfo(
                networkId = merakiProperties.meraki.networks,
                token = token,
            )
            // 로깅: API 요청 및 응답 정보를 로그에 기록
            return clientDataList
        } catch (e: Exception) {
            // 예외 처리: API 호출 중에 예외가 발생한 경우 로그에 기록하고 필요한 예외 처리 수행
            logger.error("API request failed: $e.message")
            // 예외 처리 로직 추가
            throw e // 예외를 다시 던지거나 다른 처리 방식 선택
        }
    }
}
