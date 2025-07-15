package com.tm.tsmatelier.core.service

import com.tm.tsmatelier.core.dtos.reponse.AuthResponse
import com.tm.tsmatelier.core.dtos.request.LoginRequest
import com.tm.tsmatelier.core.exception.InvalidCredentialsException
import com.tm.tsmatelier.security.JwtService
import com.tm.tsmatelier.security.RefreshTokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val refreshTokenService: RefreshTokenService,
) {
    fun login(loginRequest: LoginRequest): AuthResponse {
        try {
            val authentication =
                authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password),
                )

            SecurityContextHolder.getContext().authentication = authentication

            val accessToken = jwtService.generateAccessToken(authentication)
            val refreshToken = refreshTokenService.createNewRefreshTokenForUser(authentication.name)

            return AuthResponse(accessToken, refreshToken.token)
        } catch (ex: BadCredentialsException) {
            throw InvalidCredentialsException()
        }
    }

    fun refreshToken(token: String): AuthResponse {
        return refreshTokenService.processRefreshToken(token)
    }
}
