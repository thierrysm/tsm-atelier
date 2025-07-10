package com.tm.tsmatelier.common

object ExceptionMessage {
    const val VALUE_CANNOT_BE_NULL = "Value"
    const val REFRESH_TOKEN_NOT_FOUND = "Refresh token not found in database"
    const val REFRESH_TOKEN_REVOKED = "Refresh token was revoked"
    const val REFRESH_TOKEN_EXPIRED = "Refresh token was expired"
    const val USER_NOT_FOUND = "User with %s not found"
    const val TOKEN_COMPROMISED_EXCEPTION = "Revoked token has been reused. All sessions have been invalidated for security reasons"
    const val INVALID_JWT_EXCEPTION = "The authentication token is invalid, expired, or malformed"
    const val INVALID_CREDENTIALS_EXCEPTION = "Invalid email or  password"
}
