package com.jnu.wifi6.batch


import com.jnu.wifi6.client.MerakiClient
import com.jnu.wifi6.domain.dto.ClientData
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.LoggerFactory
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus.FINISHED
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component

@Component
class ApiCallJob(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
    val merakiClient: MerakiClient
) : QuartzJobBean() {
    private val JOB_NAME = "Meraki_정보조회"
    val BEAN_PREFIX = JOB_NAME + "_"

    val logger = LoggerFactory.getLogger(this.javaClass.simpleName)

    override fun execute(context: JobExecutionContext) {
        try {
            // API를 호출하고 값을 얻는 로직 작성
//            val apiData = apiService.callApi()
            val jobParameters = JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters()

            val jobExecution = yourStep.jobLauncher.run(yourStep.yourBatchJob(), jobParameters)

            // 성공한 경우 로깅
            println("API 호출 및 InfluxDB 저장 성공")
        } catch (e: Exception) {
            throw JobExecutionException(e)
        }
    }

    @Bean(BEAN_PREFIX + "step")
    @JobScope
    open fun eventSettlement(): Step? {
        return stepBuilderFactory[BEAN_PREFIX + "step"]
            .tasklet { contribution: StepContribution?, chunkContext: ChunkContext? ->
                logger.info(">>>>> Meraki information find")
                merakiClient.getNetworkInfo("L_646829496481105433", "a6e20dcb16b7d374169f1c8fdb53fc2375df6d94")
                FINISHED
            }
            .build()
    }
    //step
    @Bean
    fun yourStep(): Step {
        log.info("서현!")
        return stepBuilderFactory["yourStep"]
            .chunk<List<ClientData>, List<ClientData>>(1) // 처리할 데이터 사이즈
            .reader(itermReader)
            .writer(itemWriter)
            .build()
    }
}
