package com.jnu.wifi6.batch

import com.jnu.wifi6.domain.Clients
import mu.KotlinLogging.logger
import org.quartz.JobDetail
import org.quartz.SimpleTrigger
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean
import kotlin.math.log

@Configuration
class QuartzSchedulerConfig(
    val apiItemReader: ApiItemReader,
    val yourDataItemWriter: ApiItemWriter,
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
) {

    // log
    private val log = logger {}
    @Bean
    fun yourBatchJob(): Job {
        return jobBuilderFactory["yourBatchJob"]
            .start(yourStep())
            .build()
    }

    @Bean
    fun yourStep(): Step {
        log.info("서현!")
        return stepBuilderFactory["yourStep"]
            .chunk<Clients, Clients>(1) // 처리할 데이터 사이즈
            .reader(apiItemReader)
            .writer(yourDataItemWriter)
            .build()
    }

    @Bean
    fun jobDetail(): JobDetailFactoryBean {
        val factoryBean = JobDetailFactoryBean()
        factoryBean.setJobClass(ApiCallJob::class.java) // Job 클래스를 만들어야 함
        factoryBean.setDurability(true)
        return factoryBean
    }

    @Bean
    fun simpleTrigger(jobDetail: JobDetail): SimpleTriggerFactoryBean {
        val factoryBean = SimpleTriggerFactoryBean()
        factoryBean.setJobDetail(jobDetail) // JobDetail을 주입해야 함
        factoryBean.setStartDelay(3000L) // 3초 후에 실행
        factoryBean.setRepeatInterval(3000L) // 3초마다 한 번씩 실행
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY) // 무한 반복
        return factoryBean
    }

    @Bean
    fun scheduler(jobDetail: JobDetail, simpleTrigger: SimpleTrigger): SchedulerFactoryBean {
        val factoryBean = SchedulerFactoryBean()
        factoryBean.setJobDetails(jobDetail)
        factoryBean.setTriggers(simpleTrigger)
        return factoryBean
    }
}





