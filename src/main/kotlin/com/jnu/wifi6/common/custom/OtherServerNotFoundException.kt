package com.jnu.wifi6.common.custom

import com.jnu.wifi6.common.CommonCodeException
import com.jnu.wifi6.common.GlobalErrorCode

class OtherServerNotFoundException : CommonCodeException(
    GlobalErrorCode.OTHER_SERVER_NOT_FOUND,
) {
    companion object {
        val EXCEPTION: CommonCodeException = OtherServerNotFoundException()
    }
}
