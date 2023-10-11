package com.jnu.wifi6.batch

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersInvalidException
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import javax.batch.operations.JobExecutionAlreadyCompleteException

@Component
class SimpleJobScheduler(
    val jobLauncher: JobLauncher,
    val customReaderJobConfig: QuartzSchedulerConfig,
) {
    private val logger: Log = LogFactory.getLog(QuartzSchedulerConfig::class.java)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Scheduled(initialDelay = 10000, fixedDelay = 50000)
    fun runJob() {
        val jobConf = hashMapOf<String, JobParameter>()
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
