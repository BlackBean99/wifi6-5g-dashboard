package com.jnu.wifi6.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "meraki")
@ConstructorBinding
data class MerakiProperties(
    val meraki: Meraki,
) {
    data class Meraki(
        val secret: String,
        val networks: List<String>,
    )
}
