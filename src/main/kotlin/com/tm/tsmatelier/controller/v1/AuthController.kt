package com.tm.tsmatelier.controller.v1

import com.tm.tsmatelier.core.dtos.reponse.AuthResponse
import com.tm.tsmatelier.core.dtos.reponse.LoginResponse
import com.tm.tsmatelier.core.dtos.request.LoginRequest
import com.tm.tsmatelier.core.dtos.request.RegisterRequest
import com.tm.tsmatelier.core.exception.RefreshTokenException
import com.tm.tsmatelier.core.service.AuthService
import com.tm.tsmatelier.core.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService,
    private val userService: UserService,
) {
    @PostMapping("/register")
    fun register(
        @Valid @RequestBody request: RegisterRequest,
    ): ResponseEntity<String> {
        userService.registerUser(request)
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!")
    }

    @PostMapping("/login")
    fun login(
        @Valid @RequestBody loginRequest: LoginRequest,
    ): ResponseEntity<LoginResponse> {
        val authResponse = authService.login(loginRequest)
        return createAuthenticatedResponse(authResponse)
    }

    @PostMapping("/refresh")
    fun refreshToken(
        @CookieValue(name = "refreshToken") refreshTokenValue: String?,
    ): ResponseEntity<LoginResponse> {
        val token =
            refreshTokenValue.takeIf { !it.isNullOrBlank() }
                ?: throw RefreshTokenException()

        val authResponse = authService.refreshToken(token)
        return createAuthenticatedResponse(authResponse)
    }

    private fun createAuthenticatedResponse(authResponse: AuthResponse): ResponseEntity<LoginResponse> {
        val refreshTokenCookie =
            ResponseCookie.from("refreshToken", authResponse.refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60 * 60 * 24 * 7)
                .sameSite("Strict")
                .build()

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
            .body(LoginResponse(authResponse.accessToken))
    }
}
