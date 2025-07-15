package com.tm.tsmatelier.util.phoneValidator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PhoneNumberValidator : ConstraintValidator<ValidPhoneNumber, String> {
    override fun isValid(
        phone: String?,
        context: ConstraintValidatorContext?,
    ): Boolean {
        if (phone.isNullOrBlank()) {
            return false
        }

        val digitsOnly = phone.replace(Regex("\\D"), "")

        return digitsOnly.length in 10..11
    }
}
