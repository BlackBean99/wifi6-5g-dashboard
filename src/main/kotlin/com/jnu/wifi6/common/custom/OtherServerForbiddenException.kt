package com.jnu.wifi6.common.custom

import com.jnu.wifi6.common.CommonCodeException
import com.jnu.wifi6.common.GlobalErrorCode

class OtherServerForbiddenException : CommonCodeException(
    GlobalErrorCode.OTHER_SERVER_FORBIDDEN,
) {
    companion object {
        val EXCEPTION: CommonCodeException = OtherServerForbiddenException()
    }
}
