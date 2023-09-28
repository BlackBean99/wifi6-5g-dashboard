package com.depromeet.whatnow.exception.custom

import com.jnu.wifi6.common.CommonCodeException
import com.jnu.wifi6.common.GlobalErrorCode

class OtherServerInternalSeverErrorException : CommonCodeException(
    GlobalErrorCode.OTHER_SERVER_INTERNAL_SERVER_ERROR,
) {
    companion object {
        val EXCEPTION: CommonCodeException = OtherServerInternalSeverErrorException()
    }
}
