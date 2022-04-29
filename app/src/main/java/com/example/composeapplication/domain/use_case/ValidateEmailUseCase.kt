package com.example.composeapplication.domain.use_case

import android.util.Patterns

class ValidateEmailUseCase {
    operator fun invoke(email: String): ValidationResult {
        return when {
            email.isBlank() -> {
                ValidationResult(
                    successful = false,
                    errorMessage = "Email can't be empty."
                )
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
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