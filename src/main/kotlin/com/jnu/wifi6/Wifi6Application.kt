package com.jnu.wifi6

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling
@SpringBootApplication
@EnableFeignClients
@EnableBatchProcessing
@EnableScheduling
class Wifi6Application{
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<Wifi6Application>(*args)
        }
    }
}
