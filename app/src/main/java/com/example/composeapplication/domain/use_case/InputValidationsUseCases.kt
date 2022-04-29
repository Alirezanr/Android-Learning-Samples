package com.example.composeapplication.domain.use_case

data class InputValidationsUseCases(
    val validateEmail: ValidateEmailUseCase = ValidateEmailUseCase(),
    val validatePassword: ValidatePasswordUseCase = ValidatePasswordUseCase(),
    val validateRepeatedPassword: ValidateRepeatedPasswordUseCase = ValidateRepeatedPasswordUseCase(),
    val validateTerms: ValidateTermsUseCase = ValidateTermsUseCase(),
)