package com.jnu.wifi6.common

import com.depromeet.whatnow.dto.ErrorReason

interface BaseErrorCode {
    val errorReason: ErrorReason

    val explainError: String
}
