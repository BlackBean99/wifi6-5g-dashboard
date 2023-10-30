package com.jnu.wifi6.batch

import com.influxdb.client.kotlin.InfluxDBClientKotlinFactory
import com.jnu.wifi6.config.influx.InfluxProperties
import org.springframework.stereotype.Component

@Component
class InfluxReader(
    val influxProperties: InfluxProperties,
    ) {

    fun isDuplicated(id: String) :Boolean{
        val client = InfluxDBClientKotlinFactory.create(
            "http://" + influxProperties.properties.url,
            influxProperties.properties.token.toCharArray(),
            influxProperties.properties.org,
            influxProperties.properties.bucket,
        )
        val readApi = client.getQueryKotlinApi()
        return !readApi.query(
                "from(bucket: \"${influxProperties.properties.bucket}\")\n" +
                        "  |> range(start: -1d)\n" +
                        "  |> filter(fn: (r) => r[\"_measurement\"] == \"count_statistics\")\n" +
                        "  |> filter(fn: (r) => r[\"_field\"] == \"usageTotal\")\n" +
                        "  |> filter(fn: (r) => r[\"id\"] == \"${id}\")\n" +
                        "  |> aggregateWindow(every: 1d, fn: mean, createEmpty: false)\n" +
                        "  |> yield(name: \"mean\")"
        ).isEmpty
    }
}

