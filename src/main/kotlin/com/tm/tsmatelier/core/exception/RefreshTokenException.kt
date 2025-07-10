package com.tm.tsmatelier.core.exception

import com.tm.tsmatelier.common.ExceptionMessage.REFRESH_TOKEN_NOT_FOUND

class RefreshTokenException() : RuntimeException(REFRESH_TOKEN_NOT_FOUND)
