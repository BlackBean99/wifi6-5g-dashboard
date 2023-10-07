package com.jnu.wifi6.batch

import org.quartz.JobDetail
import org.quartz.SimpleTrigger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean

@Configuration
class QuartzSchedulerConfig() {
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





