package com.jnu.wifi6.config

import com.jnu.wifi6.config.influx.InfluxProperties
import org.springframework.boot.autoconfigure.influx.InfluxDbProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(InfluxProperties::class, MerakiProperties::class)
@Configuration
class EnableConfigProperties
