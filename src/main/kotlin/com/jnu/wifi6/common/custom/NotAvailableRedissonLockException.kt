package com.depromeet.whatnow.exception.custom

import com.jnu.wifi6.common.CommonCodeException
import com.jnu.wifi6.common.GlobalErrorCode

class NotAvailableRedissonLockException : CommonCodeException(
    GlobalErrorCode.NOT_AVAILABLE_REDISSON_LOCK,
) {
    companion object {
        val EXCEPTION: CommonCodeException = NotAvailableRedissonLockException()
    }
}
