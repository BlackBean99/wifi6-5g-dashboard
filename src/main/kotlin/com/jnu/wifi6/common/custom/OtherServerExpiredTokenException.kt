package com.jnu.wifi6.common.custom

import com.jnu.wifi6.common.CommonCodeException
import com.jnu.wifi6.common.GlobalErrorCode

class OtherServerExpiredTokenException : CommonCodeException(
    GlobalErrorCode.OTHER_SERVER_EXPIRED_TOKEN,
) {
    companion object {
        val EXCEPTION: CommonCodeException = OtherServerExpiredTokenException()
    }
}
