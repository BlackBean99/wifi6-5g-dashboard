package com.jnu.wifi6.service

import com.influxdb.client.InfluxDBClient
import com.influxdb.client.kotlin.InfluxDBClientKotlinFactory
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.take
import org.springframework.boot.autoconfigure.influx.InfluxDbProperties

@Service
class ClientService(
) {
    fun getUserInformation(args: Array<String>) = runBlocking {

        val influxProperties = InfluxDbProperties();
        val influxDBClient = InfluxDBClientKotlinFactory
            .create(url = influxProperties.url,
                username = influxProperties.user,
                password = influxProperties.password)

        val fluxQuery = ("from(bucket: \"my-bucket\")\n"
                + " |> range(start: -1d)"
                + " |> filter(fn: (r) => (r[\"_measurement\"] == \"cpu\" and r[\"_field\"] == \"usage_system\"))")

        //Result is returned as a stream
        val results = influxDBClient.getQueryKotlinApi().query(fluxQuery)
        results
            //filter on client side using `filter` built-in operator
            .filter { "cpu0" == it.getValueByKey("cpu") }
            //take first 20 records
            .take(20)
            //print results
            .consumeEach { println("Measurement: ${it.measurement}, value: ${it.value}") }

        influxDBClient.close()
    }
}