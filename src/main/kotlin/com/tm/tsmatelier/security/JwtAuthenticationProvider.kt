package com.tm.tsmatelier.security

import com.tm.tsmatelier.core.entity.UserEntity
import com.tm.tsmatelier.core.exception.InvalidJwtException
import com.tm.tsmatelier.core.repository.UserRepository
import com.tm.tsmatelier.util.orThrow
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationProvider(
    private val jwtDecoder: JwtDecoder,
    private val userRepository: UserRepository,
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val jwtAuthentication = authentication as JwtAuthentication
        val jwt: String? = jwtAuthentication.jwt

        try {
            val decodedJwt =
                jwt?.let { jwtDecoder.decode(it) }
                    ?: throw BadCredentialsException("Something went wrong")

            val authenticatedUser = userRepository.findByEmail(decodedJwt.subject).orThrow().toAuthenticatedUser()
            return JwtAuthentication(jwt = jwt, authenticated = true, authenticatedUser = authenticatedUser)
        } catch (ex: JwtException) {
            throw InvalidJwtException()
        }
    }

    override fun supports(authentication: Class<*>?) = JwtAuthentication::class.java == authentication

    private fun UserEntity.toAuthenticatedUser() = AuthenticatedUser(this)
}
