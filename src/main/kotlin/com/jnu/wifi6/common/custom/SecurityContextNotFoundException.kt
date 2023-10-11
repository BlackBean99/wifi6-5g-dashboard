package com.jnu.wifi6.common.custom

import com.jnu.wifi6.common.CommonCodeException
import com.jnu.wifi6.common.GlobalErrorCode

class SecurityContextNotFoundException : CommonCodeException(
    GlobalErrorCode.SECURITY_CONTEXT_NOT_FOUND,
) {
    companion object {
        val EXCEPTION: CommonCodeException = SecurityContextNotFoundException()
    }
}
