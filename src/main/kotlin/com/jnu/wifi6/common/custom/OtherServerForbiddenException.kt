package com.depromeet.whatnow.exception.custom

import com.jnu.wifi6.common.GlobalErrorCode
import com.jnu.wifi6.common.CommonCodeException

class OtherServerForbiddenException : CommonCodeException(
    GlobalErrorCode.OTHER_SERVER_FORBIDDEN,
) {
    companion object {
        val EXCEPTION: CommonCodeException = OtherServerForbiddenException()
    }
}
