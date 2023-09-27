package com.depromeet.whatnow.exception.custom

import com.jnu.wifi6.common.GlobalErrorCode

import com.jnu.wifi6.common.CommonCodeException

class OtherServerNotFoundException : CommonCodeException(
    GlobalErrorCode.OTHER_SERVER_NOT_FOUND,
) {
    companion object {
        val EXCEPTION: CommonCodeException = OtherServerNotFoundException()
    }
}
