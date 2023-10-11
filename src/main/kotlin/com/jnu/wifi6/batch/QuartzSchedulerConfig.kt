package com.jnu.wifi6.batch

import com.jnu.wifi6.domain.dto.ClientData
import mu.KotlinLogging.logger
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
//    val itermReader: ApiItemReader,
//    val itemWriter: ApiItemWriter,
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
) {

    private final val CUSTOM_READER_JOB = "CUSTOM_READER_JOB"
    private final val CUSTOM_READER_JOB_STEP = CUSTOM_READER_JOB + "_STEP"
    private final val CHUNK_SIZE = 10

    // log
    private val log = logger {}
/*    @Bean
    fun yourBatchJob(): Job {
        return jobBuilderFactory["yourBatchJob"]
            .start(yourStep())
            .build()
    }*/

//    @Bean
//    fun jobDetail(): JobDetailFactoryBean {
//        val factoryBean = JobDetailFactoryBean()
//        factoryBean.setJobClass(ApiCallJob::class.java) // Job 클래스를 만들어야 함
//        //yourBatchJob 실행
//        factoryBean.setDurability(true)
//        return factoryBean
//    }

    @Bean
    fun customReaderStep(): Step {
        return stepBuilderFactory.get(CUSTOM_READER_JOB_STEP)
            .chunk<List<ClientData>, List<ClientData>>(CHUNK_SIZE)
            .reader(reader())
            .writer(writer())
            .build()
    }

//    @Bean
//    fun simpleTrigger(jobDetail: JobDetail): SimpleTriggerFactoryBean {
//        val factoryBean = SimpleTriggerFactoryBean()
//        factoryBean.setJobDetail(jobDetail) // JobDetail을 주입해야 함
//        factoryBean.setStartDelay(3000L) // 3초 후에 실행
//        factoryBean.setRepeatInterval(3000L) // 3초마다 한 번씩 실행
//        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY) // 무한 반복
//        return factoryBean
//    }

    @Bean
    fun customReaderJob(): Job {
        return jobBuilderFactory.get(CUSTOM_READER_JOB)
            .start(customReaderStep())
            .build()
    }

//    @Bean
//    fun scheduler(jobDetail: JobDetail, simpleTrigger: SimpleTrigger): SchedulerFactoryBean {
//        val factoryBean = SchedulerFactoryBean()
//        factoryBean.setJobDetails(jobDetail)
//        factoryBean.setTriggers(simpleTrigger)
//        return factoryBean
//    }
    @Bean
    @StepScope
    fun reader(): ApiItemReader {
        return ApiItemReader()
    }

    /*@Bean
    fun processor(): ItemProcessor<List<ClientData>, Book>{
        return ItemProcessor {
            it.author = "Author. " + it.author
            it
        }
    }*/
    @Bean
    fun writer(): ItemWriter<List<ClientData>> {
        return ApiItemWriter()
    }
}
