package com.jnu.wifi6.batch.job

import com.jnu.wifi6.batch.parameter.DateJobParameter
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MerakiToClient(
    val stepBuilder: StepBuilder,
    val DateJobParameter: DateJobParameter,
): {
    private val JOB_NAME: String = "merakiToClient"
    private val BEAN_PREFIX: String = JOB_NAME + "_"

    @Bean(BEAN_PREFIX + "dateJobParameter")
    @StepScope
    fun dateJobParameter(): DateJobParameter {
        return DateJobParameter
    }


}