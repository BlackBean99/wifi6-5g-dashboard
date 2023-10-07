package com.jnu.wifi6.batch


import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ApiCallJob(
) : Job {
    val logger = LoggerFactory.getLogger(this.javaClass.simpleName)

    override fun execute(context: JobExecutionContext) {
        try {
            // API를 호출하고 값을 얻는 로직 작성
//            val apiData = apiService.callApi()
            logger.info("ApiCallJob execute")

            // InfluxDB에 데이터를 저장하는 로직 작성
//            influxDBService.saveData(apiData)
            logger.info("db save")
            // 성공한 경우 로깅
            println("API 호출 및 InfluxDB 저장 성공")
        } catch (e: Exception) {
            // 실패한 경우 예외 처리
            println("API 호출 또는 InfluxDB 저장 중 오류 발생: ${e.message}")
        }
    }
}
