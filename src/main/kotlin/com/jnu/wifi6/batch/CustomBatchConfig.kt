//package com.jnu.wifi6.batch
//
//import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer
//import org.springframework.context.annotation.Configuration
//import javax.sql.DataSource
//
//@Configuration
//class CustomBatchConfig(
//    dataSource: DataSource?,
////    batchFailureService: BatchFailureService,
//    ) : DefaultBatchConfigurer(dataSource!!) {
////    override fun initialize() {
////        // Initialize batch meta data tables
////        batchFailureService.failAllJobs()
////        super.initialize()
////    }
//}