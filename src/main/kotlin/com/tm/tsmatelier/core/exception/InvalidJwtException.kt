package com.tm.tsmatelier.core.exception

import com.tm.tsmatelier.common.ExceptionMessage.INVALID_JWT_EXCEPTION
import org.springframework.security.core.AuthenticationException

class InvalidJwtException : AuthenticationException(INVALID_JWT_EXCEPTION)
