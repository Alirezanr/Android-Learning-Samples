package com.example.composeapplication.domain.use_case

class ValidateTermsUseCase {
    operator fun invoke(acceptedTerms: Boolean): ValidationResult {
        return when {
            !acceptedTerms -> {
                ValidationResult(
                    successful = false,
                    errorMessage = "Please accept the terms."
                )
            }
            else -> {
                ValidationResult(true)
            }
        }
    }
}