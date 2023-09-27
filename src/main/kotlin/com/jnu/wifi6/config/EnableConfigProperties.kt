package com.jnu.wifi6.config

import org.springframework.boot.autoconfigure.influx.InfluxDbProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(InfluxDbProperties::class)
@Configuration
class EnableConfigProperties