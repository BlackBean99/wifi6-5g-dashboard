package com.jnu.wifi6.config.influx

import com.jnu.wifi6.common.custom.OtherServerBadRequestException
import com.jnu.wifi6.common.custom.OtherServerExpiredTokenException
import com.jnu.wifi6.common.custom.OtherServerForbiddenException
import com.jnu.wifi6.common.custom.OtherServerUnauthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class InfluxDBErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            when (response.status()) {
                401 -> throw OtherServerUnauthorizedException.EXCEPTION
                403 -> throw OtherServerForbiddenException.EXCEPTION
                419 -> throw OtherServerExpiredTokenException.EXCEPTION
                else -> throw OtherServerBadRequestException.EXCEPTION
            }
        }
        return FeignException.errorStatus(methodKey, response)
    }
}
