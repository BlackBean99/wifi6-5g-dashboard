package com.jnu.wifi6.batch

import com.jnu.wifi6.config.influx.InfluxProperties
import com.jnu.wifi6.domain.dto.ClientData
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuartzSchedulerConfig(
    val influxProperties: InfluxProperties,
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
) {
    private final val CUSTOM_READER_JOB = "CUSTOM_READER_JOB"
    private final val CUSTOM_READER_JOB_STEP = CUSTOM_READER_JOB + "_STEP"
    private final val CHUNK_SIZE = 1

    @Bean
    fun customReaderStep(): Step {
        return stepBuilderFactory.get(CUSTOM_READER_JOB_STEP)
            .chunk<List<ClientData>, List<ClientData>>(CHUNK_SIZE)
            .reader(reader())
            .writer(writer())
            .writer(countWriter())
            .build()
    }

    @Bean
    fun customReaderJob(): Job {
        return jobBuilderFactory.get(CUSTOM_READER_JOB)
            .start(customReaderStep())
            .build()
    }

    @Bean
    @StepScope
    fun reader(): ApiItemReader {
        return ApiItemReader()
    }

    @Bean
    @StepScope
    fun writer(): ItemWriter<List<ClientData>> {
        return ApiItemWriter(influxProperties)
    }

    @Bean
    @StepScope
    fun countWriter(): ItemWriter<List<ClientData>> {
        return CountItemWriter(influxProperties)
    }
}
