package com.tm.tsmatelier.util

import com.tm.tsmatelier.common.ExceptionMessage.VALUE_CANNOT_BE_NULL

fun <T> T?.orThrow() = this ?: error(VALUE_CANNOT_BE_NULL)
