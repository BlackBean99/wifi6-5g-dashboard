package com.depromeet.whatnow.annotation

import com.jnu.wifi6.common.BaseErrorCode
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiErrorCodeExample(val value: KClass<out BaseErrorCode>)
