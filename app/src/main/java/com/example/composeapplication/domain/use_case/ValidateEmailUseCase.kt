package com.example.composeapplication.domain.use_case

import com.example.composeapplication.domain.util.Constants
import com.example.composeapplication.domain.util.isValidEmail

class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidationResult {
        return when {
            email.isBlank() -> {
                ValidationResult(
                    successful = false,
                    errorMessage = Constants.BLANK_EMAIL_ADDRESS_ERROR
                )
            }
            !email.isValidEmail() -> {
                ValidationResult(
                    successful = false,
                    errorMessage = Constants.INVALID_EMAIL_ADDRESS_ERROR
                )
            }
            else -> {
                ValidationResult(true)
            }
        }
    }
}