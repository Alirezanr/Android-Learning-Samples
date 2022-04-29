package com.example.composeapplication.domain.use_case

class ValidatePasswordUseCase {
    operator fun invoke(password: String): ValidationResult {
        return when {
            password.length < 8 -> {
                ValidationResult(
                    successful = false,
                    errorMessage = "Password minimum length is 8 characters."
                )
            }
            !(password.any { it.isDigit() } && password.any { it.isLetter() }) -> {
                ValidationResult(
                    successful = false,
                    errorMessage = "Password needs to contain at least one letter and one digit."
                )
            }
            else -> {
                ValidationResult(true)
            }
        }
    }
}