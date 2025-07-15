package com.tm.tsmatelier.core.exception

import com.tm.tsmatelier.common.ExceptionMessage.REFRESH_TOKEN_REVOKED

class RefreshTokenRevokedException : RuntimeException(REFRESH_TOKEN_REVOKED)
