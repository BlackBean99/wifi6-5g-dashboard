package com.jnu.wifi6.common

class CommonDynamicException(
    val status: Int,
    val code: String,
    val reason: String?,
) : RuntimeException()
