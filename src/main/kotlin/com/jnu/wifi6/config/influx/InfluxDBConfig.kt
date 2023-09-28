package com.jnu.wifi6.config.influx

import com.influxdb.client.kotlin.InfluxDBClientKotlinFactory
import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(InfluxDBErrorDecoder::class)
class InfluxDBConfig {

    @Bean
    fun encoder(converters: ObjectFactory<HttpMessageConverters>): Encoder {
        return SpringFormEncoder(SpringEncoder(converters))
    }
    val influxDBClient = InfluxDBClientKotlinFactory
        .create("http://localhost:8086", "my-token".toCharArray(), "my-org")
}
