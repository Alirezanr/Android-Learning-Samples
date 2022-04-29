package com.example.composeapplication.domain.use_case

class ValidateRepeatedPasswordUseCase {
    operator fun invoke(password: String, repeatedPassword: String): ValidationResult {
        return when {
            password != repeatedPassword -> {
                ValidationResult(
                    successful = false,
                    errorMessage = "Passwords don't match."
                )
            }
            else -> {
                ValidationResult(true)
            }
        }
    }
}