package com.example.composeapplication.domain.use_case

import com.example.composeapplication.domain.util.isValidEmail

class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidationResult {
        return when {
            email.isBlank() -> {
                ValidationResult(
                    successful = false,
                    errorMessage = "Email can't be empty."
                )
            }
            !email.isValidEmail() -> {
                ValidationResult(
                    successful = false,
                    errorMessage = "Enter a valid email address."
                )
            }
            else -> {
                ValidationResult(true)
            }
        }
    }
}