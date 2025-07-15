package com.tm.tsmatelier.core.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.tm.tsmatelier.config.EnvironmentConfiguration
import com.tm.tsmatelier.controller.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper,
    private val environmentConfiguration: EnvironmentConfiguration,
) : AuthenticationEntryPoint {
    private val log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint::class.java)

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException,
    ) {
        log.warn(
            "Acesso não autenticado negado para '${request.requestURI}'. Causa: ${authException.javaClass.simpleName}",
            authException,
        )

        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        val errorResponse =
            ErrorResponse(
                httpStatus = HttpStatus.UNAUTHORIZED,
                exception = authException,
                request = request,
            )

        val body =
            if (environmentConfiguration.isProduction()) {
                errorResponse.copy(exception = null, trace = null)
            } else {
                errorResponse
            }

        objectMapper.writeValue(response.writer, body)
    }
}
