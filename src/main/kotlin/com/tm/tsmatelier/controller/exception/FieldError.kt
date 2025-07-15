package com.tm.tsmatelier.controller.exception

import com.fasterxml.jackson.annotation.JsonProperty

data class FieldError(
    @field:JsonProperty("field")
    val field: String,
    @field:JsonProperty("message")
    val message: String,
)
