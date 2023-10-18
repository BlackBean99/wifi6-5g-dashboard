package com.jnu.wifi6.config.influx

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "influx")
@ConstructorBinding
data class InfluxProperties(
    val properties: Meraki,
) {
    data class Meraki(
        val url: String,
        val token: String,
        val username: String,
        val password: String,
        val org: String,
        val bucket: String,
    )
}
