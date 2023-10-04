package com.jnu.wifi6.batch

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private val log = KotlinLogging.logger {}
@Configuration
class SingleStepJobConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {
    @Bean
    fun multipleStepJob(): Job {
        return jobBuilderFactory["multipleStepJob"]
            .start(startStep())
            .next(nextStep())
            .next(lastStep())
            .build()
    }
    @Bean
    fun startStep(): Step {
        return stepBuilderFactory["startStep"]
            .tasklet { _: StepContribution, _: ChunkContext ->
                log.info { "Start Step!!" }
                RepeatStatus.FINISHED
            }
            .build()
    }
    @Bean
    fun nextStep(): Step {
        return stepBuilderFactory["nextStep"]
            .tasklet { _: StepContribution, _: ChunkContext ->
                log.info { "Next Step!!" }
                RepeatStatus.FINISHED
            }
            .build()
    }
    @Bean
    fun lastStep(): Step {
        return stepBuilderFactory["lastStep"]
            .tasklet { _: StepContribution, _: ChunkContext ->
                log.info { "Last Step!!" }
                RepeatStatus.FINISHED
            }
            .build()
    }
}