package com.tm.tsmatelier.controller.exception

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(
    @field:JsonProperty("timestamp")
    val timestamp: LocalDateTime,
    @field:JsonProperty("status")
    val status: Int,
    @field:JsonProperty("error")
    val error: String,
    @field:JsonProperty("path")
    val path: String,
    @field:JsonProperty("exception")
    val exception: String? = null,
    @field:JsonProperty("message")
    val message: String? = null,
    @field:JsonProperty("errors")
    val errors: List<FieldError>? = null,
    @field:JsonProperty("trace")
    val trace: String? = null,
) {
    constructor(
        httpStatus: HttpStatus,
        exception: Exception,
        request: HttpServletRequest,
        errors: List<FieldError>? = null,
    ) : this(
        timestamp = LocalDateTime.now(),
        status = httpStatus.value(),
        error = httpStatus.reasonPhrase,
        path = request.requestURI,
        exception = exception::class.simpleName,
        message = exception.message,
        errors = errors,
        trace = exception.stackTraceToString(),
    )
}
