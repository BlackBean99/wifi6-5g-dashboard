package com.jnu.wifi6.common

import com.depromeet.whatnow.dto.ErrorReason

open class CommonCodeException(
    val errorCode: BaseErrorCode,
) : RuntimeException() {

    val errorReason: ErrorReason
        get() {
            return errorCode.errorReason
        }
}
