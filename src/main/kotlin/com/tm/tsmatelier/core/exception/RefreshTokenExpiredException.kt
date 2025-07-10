package com.tm.tsmatelier.core.exception

import com.tm.tsmatelier.common.ExceptionMessage.REFRESH_TOKEN_EXPIRED

class RefreshTokenExpiredException : RuntimeException(REFRESH_TOKEN_EXPIRED)
