package com.depromeet.whatnow.exception.custom

import com.jnu.wifi6.common.GlobalErrorCode
import com.jnu.wifi6.common.CommonCodeException

class SecurityContextNotFoundException : CommonCodeException(
    GlobalErrorCode.SECURITY_CONTEXT_NOT_FOUND,
) {
    companion object {
        val EXCEPTION: CommonCodeException = SecurityContextNotFoundException()
    }
}
