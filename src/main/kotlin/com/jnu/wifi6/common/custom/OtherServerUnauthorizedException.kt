package com.jnu.wifi6.common.custom

import com.jnu.wifi6.common.CommonCodeException
import com.jnu.wifi6.common.GlobalErrorCode

class OtherServerUnauthorizedException : CommonCodeException(
    GlobalErrorCode.OTHER_SERVER_UNAUTHORIZED,
) {
    companion object {
        val EXCEPTION: CommonCodeException = OtherServerUnauthorizedException()
    }
}
