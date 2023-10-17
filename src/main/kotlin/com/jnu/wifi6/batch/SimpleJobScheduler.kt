package com.jnu.wifi6.batch

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersInvalidException
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import javax.batch.operations.JobExecutionAlreadyCompleteException

@Component
class SimpleJobScheduler(
    val jobLauncher: JobLauncher,
    val customReaderJobConfig: QuartzSchedulerConfig,
) {
    private val logger: Log = LogFactory.getLog(QuartzSchedulerConfig::class.java)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'")

//     매 15초마다 실행
    @Scheduled(initialDelay = 500000, fixedDelay = 500000)
//    @Scheduled(fixedDelay = 100000)
    fun runJob() {
        val jobConf = hashMapOf<String, JobParameter>()
        logger.info("Job Started at :" + DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.now()))
        jobConf["time"] = JobParameter(dateFormat.format(System.currentTimeMillis()))
        val jobParameters = JobParameters(jobConf)
        try {
            jobLauncher.run(customReaderJobConfig.customReaderJob(), jobParameters)
        } catch (e: JobExecutionAlreadyCompleteException) {
            logger.error(e.localizedMessage)
        } catch (e: JobExecutionAlreadyRunningException) {
            logger.error(e.localizedMessage)
        } catch (e: JobParametersInvalidException) {
            logger.error(e.localizedMessage)
        }
    }
}