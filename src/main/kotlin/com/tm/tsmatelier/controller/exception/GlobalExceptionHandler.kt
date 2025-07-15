package com.tm.tsmatelier.controller.exception

import com.tm.tsmatelier.config.EnvironmentConfiguration
import com.tm.tsmatelier.core.exception.InvalidCredentialsException
import com.tm.tsmatelier.core.exception.InvalidJwtException
import com.tm.tsmatelier.core.exception.RefreshTokenException
import com.tm.tsmatelier.core.exception.RefreshTokenExpiredException
import com.tm.tsmatelier.core.exception.RefreshTokenRevokedException
import com.tm.tsmatelier.core.exception.TokenCompromisedException
import com.tm.tsmatelier.core.exception.UserAlreadyExistsException
import com.tm.tsmatelier.core.exception.UserNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler(
    private val environmentConfiguration: EnvironmentConfiguration,
) {
    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(
        exception: AuthenticationException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.UNAUTHORIZED, exception, request)

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(
        exception: UserNotFoundException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.NOT_FOUND, exception, request)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        val fieldErrors = exception.bindingResult.fieldErrors.map { FieldError(it.field, it.defaultMessage.orEmpty()) }
        return buildResponse(HttpStatus.BAD_REQUEST, exception, request, fieldErrors)
    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExistsException(
        exception: UserAlreadyExistsException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.CONFLICT, exception, request)

    @ExceptionHandler(RefreshTokenException::class)
    fun handleRefreshTokenException(
        exception: RefreshTokenException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.UNAUTHORIZED, exception, request)

    @ExceptionHandler(RefreshTokenExpiredException::class)
    fun handleRefreshTokenExpiredException(
        exception: RefreshTokenExpiredException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.UNAUTHORIZED, exception, request)

    @ExceptionHandler(RefreshTokenRevokedException::class)
    fun handleRefreshTokenRevokedException(
        exception: RefreshTokenRevokedException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.UNAUTHORIZED, exception, request)

    @ExceptionHandler(TokenCompromisedException::class)
    fun handleTokenCompromisedException(
        exception: TokenCompromisedException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.UNAUTHORIZED, exception, request)

    @ExceptionHandler(InvalidJwtException::class)
    fun handleInvalidJwtException(
        exception: InvalidJwtException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.UNAUTHORIZED, exception, request)

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
        exception: HttpMessageNotReadableException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.BAD_REQUEST, exception, request)

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentialsException(
        exception: InvalidCredentialsException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> = buildResponse(HttpStatus.UNAUTHORIZED, exception, request)

    private fun buildResponse(
        httpStatus: HttpStatus,
        exception: Exception,
        request: HttpServletRequest,
        errors: List<FieldError>? = null,
        overrideMessage: String? = null,
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(httpStatus, exception, request, errors)

        val responseWithMessage = overrideMessage?.let { errorResponse.copy(message = it) } ?: errorResponse

        val body =
            if (environmentConfiguration.isProduction()) {
                responseWithMessage.copy(exception = null, trace = null)
            } else {
                responseWithMessage
            }
        return ResponseEntity.status(httpStatus).body(body)
    }
}
