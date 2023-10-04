package com.jnu.wifi6.batch.parameter

import com.jnu.wifi6.domain.InfluxAdaptor
import org.springframework.beans.factory.annotation.Value
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.springframework.batch.core.JobParametersInvalidException
import java.util.Objects

// https://www.youtube.com/watch?v=_nkJkWVH-mo
// 우아한 스프링 배치 이동욱님 22분 참조.
class DateJobParameter(
    var date: LocalDate,
    val influxAdaptor: InfluxAdaptor,
) {
    @Value("#{jobParameters[date]}")
    fun setDate(date: String) {
        if(Objects.isNull(date)) {
            throw JobParametersInvalidException("날짜 형식의 파라미터가 필요합니다")
        }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        this.date = LocalDate.parse(date, formatter)
    }
}