//package com.jnu.wifi6.config.influx
//
//import com.influxdb.client.write.Point
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.boot.context.properties.EnableConfigurationProperties
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.influxdb.InfluxDBConnectionFactory
//
///*
//@Import(InfluxDBErrorDecoder::class)
//class InfluxDBConfig() {
//
//    @Bean
//    fun encoder(converters: ObjectFactory<HttpMessageConverters>): Encoder {
//        return SpringFormEncoder(SpringEncoder(converters))
//    }
//    val influxDBClient = InfluxDBClientKotlinFactory
//        .create("http://localhost:8086", "my-token".toCharArray(), "my-org")
//}
//*/
//
//@Configuration
//@EnableConfigurationProperties(InfluxDBProperties::class)
//class InfluxDBConfiguration {
//    @Bean
//    fun connectionFactory(@Qualifier("spring.influxdb-org.springframework.data.influxdb.InfluxDBProperties") properties: InfluxDBProperties?): InfluxDBConnectionFactory {
//        return InfluxDBConnectionFactory(properties)
//    }
//
//    @Bean
//    fun influxDBTemplate(connectionFactory: InfluxDBConnectionFactory?): InfluxDBTemplate<Point> {
//        /*
//         * You can use your own 'PointCollectionConverter' implementation, e.g. in case
//         * you want to use your own custom measurement object.
//         */
//        return InfluxDBTemplate(connectionFactory, PointConverter())
//    }
//
//    @Bean
//    fun defaultTemplate(connectionFactory: InfluxDBConnectionFactory?): DefaultInfluxDBTemplate {
//        /*
//         * If you are just dealing with Point objects from 'influxdb-java' you could
//         * also use an instance of class DefaultInfluxDBTemplate.
//         */
//        return DefaultInfluxDBTemplate(connectionFactory)
//    }
//}