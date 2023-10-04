//package com.jnu.wifi6.batch.parameter
//
//import com.jnu.wifi6.domain.Clients
//import com.jnu.wifi6.helper.MerakiHelper
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.batch.core.JobParametersInvalidException
//import java.util.Objects
//
//// https://www.youtube.com/watch?v=_nkJkWVH-mo
//// 우아한 스프링 배치 이동욱님 22분 참조.
//class InfluxJobParameter(
//    val merakiHelper: MerakiHelper,
//    var clients: Clients
//) {
//    @Value("#{jobParameters[networkId]}")
//    fun setDate(networkId: String) {
//        if(Objects.isNull(networkId)) {
//            throw JobParametersInvalidException("날짜 형식의 파라미터가 필요합니다")
//        }
//        this.clients = merakiHelper.getClientsByNetworkId(networkId)
//    }
//}