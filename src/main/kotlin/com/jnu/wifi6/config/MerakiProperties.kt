package com.jnu.wifi6.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "properties")
@ConstructorBinding
data class MerakiProperties(
    val meraki: Meraki,
) {
    data class Meraki(
        val secret: String,
        val networks: String,
    )
}
