package com.depromeet.whatnow.exception.custom

import com.jnu.wifi6.common.CommonCodeException
import com.jnu.wifi6.common.GlobalErrorCode


class InvalidTokenException : CommonCodeException(
    GlobalErrorCode.INVALID_TOKEN,
) {
    companion object {
        val EXCEPTION: CommonCodeException = InvalidTokenException()
    }
}
