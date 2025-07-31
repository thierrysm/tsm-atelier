package com.tm.tsmatelier.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.tm.tsmatelier.controller.exception.ErrorResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtAuthenticationManager: JwtAuthenticationManager,
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring(7)

        try {
            val authentication = jwtAuthenticationManager.authenticate(JwtAuthentication(jwt))
            if (authentication.isAuthenticated) {
                SecurityContextHolder.getContext().authentication = authentication
            }
            filterChain.doFilter(request, response)
        } catch (exception: Exception) {
            sendErrorResponse(request, response, exception)
        }
    }

    private fun sendErrorResponse(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: Exception,
    ) {
        val errorResponse =
            ErrorResponse(
                httpStatus = HttpStatus.UNAUTHORIZED,
                exception = exception,
                request = request,
            )
        response.status = errorResponse.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}
