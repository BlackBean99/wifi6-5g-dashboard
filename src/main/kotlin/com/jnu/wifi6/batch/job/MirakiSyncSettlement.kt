package com.jnu.wifi6.batch.job

import com.jnu.wifi6.domain.InfluxAdaptor
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Configuration

@Configuration
class MirakiSyncSettlement(
    val stepBuilderFactory: StepBuilderFactory,
    val influxAdaptor: InfluxAdaptor,
) {
    private val JOB_NAME: String = "이벤트정산요약"
    private val BEAN_PREFIX: String = JOB_NAME + "_"
    private val jobBuilderFactory: JobBuilderFactory = null
}