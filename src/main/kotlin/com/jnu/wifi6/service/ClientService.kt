//package com.jnu.wifi6.service
//
//import com.influxdb.client.kotlin.InfluxDBClientKotlinFactory
//import com.jnu.wifi6.helper.MerakiHelper
//import kotlinx.coroutines.channels.consumeEach
//import kotlinx.coroutines.channels.take
//import kotlinx.coroutines.runBlocking
//import org.springframework.boot.autoconfigure.influx.InfluxDbProperties
//import org.springframework.stereotype.Service
//
//@Service
//class ClientService(
//    val merakiHelper: MerakiHelper,
//) {
//    fun getUserInformation(networkId: String) = runBlocking {
//        val influxProperties = InfluxDbProperties()
//
//        val influxDBClient = InfluxDBClientKotlinFactory
//            .create(
//                url = influxProperties.url,
//                username = influxProperties.user,
//                password = influxProperties.password.toCharArray() // CharArray를 사용하는 경우
//            )
//
//        val fluxQuery = (
//            "from(bucket: \"my-bucket\")\n" +
//                " |> range(start: -1d)" +
//                " |> filter(fn: (r) => (r[\"_measurement\"] == \"cpu\" and r[\"_field\"] == \"usage_system\"))"
//            )
//
//        // Result is returned as a stream
//        val results = influxDBClient.getQueryKotlinApi().query(fluxQuery)
//        results
//            // filter on client side using `filter` built-in operator
////            .filter { "cpu0" == it.getValueByKey("cpu") }
//            // take first 20 records
//            // print results
//            .consumeEach { println("Measurement: ${it.measurement}, value: ${it.value}") }
//
//        influxDBClient.close()
//    }
//}
