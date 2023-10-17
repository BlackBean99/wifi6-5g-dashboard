//package com.jnu.wifi6.batch.service
//
//import org.springframework.batch.core.ExitStatus
//import org.springframework.batch.core.JobParameters
//import org.springframework.batch.core.explore.JobExplorer
//import org.springframework.batch.core.launch.JobLauncher
//import org.springframework.batch.core.repository.JobRepository
//import org.springframework.stereotype.Component
//import java.util.Date
//
//@Component
//class BatchFailureService(
//    private val jobRepository: JobRepository,
//    private val jobExplorer: JobExplorer,
//    private val jobLauncher: JobLauncher
//) {
//    fun failAllJobs() {
//        val jobInstances = jobExplorer.findJobInstancesByJobName("yourJobName", 0, Int.MAX_VALUE)
//        for (jobInstance in jobInstances) {
//            val jobExecutions = jobExplorer.getJobExecutions(jobInstance)
//            for (jobExecution in jobExecutions) {
//                if (!jobExecution.status.isUnsuccessful) {
////                    val job = jobRepository.getJobInstance(jobExecution.jobInstance.instanceId)?.job
//                    val job = jobRepository.getLastJobExecution(jobExecution.jobInstance, jobExecution.jobInstance.jobName)
//
//                    if (job != null) {
////                        jobExecution.status = ExitStatus.FAILED
//                        val jobParameters = JobParameters()
//                        val newJobExecution = jobLauncher.run(job, jobParameters)
//                        newJobExecution.endTime = Date()
//                        jobRepository.update(newJobExecution)
//                    }
//                }
//            }
//        }
//    }
//}