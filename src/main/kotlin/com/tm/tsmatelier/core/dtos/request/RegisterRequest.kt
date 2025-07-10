package com.tm.tsmatelier.core.dtos.request

import com.tm.tsmatelier.util.phoneValidator.ValidPhoneNumber
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterRequest(
    @field:NotBlank(message = "Full name cannot be blank")
    val fullName: String,
    // TODO  @field:CPF(message = "CPF is invalid")
    @field:NotBlank(message = "CPF cannot be blank")
    @field:Size(min = 11, max = 11, message = "CPF must have 11 digits")
    val cpf: String,
    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Invalid email format")
    val email: String,
    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(min = 8, message = "Password must be at least 8 characters long")
    val password: String,
    @field:NotBlank(message = "Phone cannot be blank")
    @field:ValidPhoneNumber
    val phone: String,
)
