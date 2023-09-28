package com.jnu.wifi6.config.meraki

import feign.codec.ErrorDecoder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(MerakiErrorDecoder::class)
class MerakiConfig {
    @Bean
    @ConditionalOnMissingBean(value = [ErrorDecoder::class])
    fun commonFeignErrorDecoder(): MerakiErrorDecoder {
        return MerakiErrorDecoder()
    }
}
