package com.tm.tsmatelier.core.exception

import com.tm.tsmatelier.common.ExceptionMessage.INVALID_CREDENTIALS_EXCEPTION
import java.lang.RuntimeException

class InvalidCredentialsException : RuntimeException(INVALID_CREDENTIALS_EXCEPTION)
