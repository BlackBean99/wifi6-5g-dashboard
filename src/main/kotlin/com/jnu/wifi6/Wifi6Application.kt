package com.jnu.wifi6

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
class Wifi6Application
fun main(args: Array<String>) {
    runApplication<Wifi6Application>(*args)
}
