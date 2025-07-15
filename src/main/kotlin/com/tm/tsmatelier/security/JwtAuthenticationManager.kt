package com.tm.tsmatelier.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationManager(
    private val jwtAuthenticationProvider: JwtAuthenticationProvider,
) : AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication {
        if (jwtAuthenticationProvider.supports(authentication::class.java)) {
            return jwtAuthenticationProvider.authenticate(authentication)
        }
        throw BadCredentialsException("Unsupported authentication type: ${authentication::class.simpleName}")
    }
}
