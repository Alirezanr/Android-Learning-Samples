package com.example.composeapplication.domain.use_case

data class InputValidationsUseCases(
    private val validateEmail: ValidateEmailUseCase = ValidateEmailUseCase(),
    private val ValidatePassword: ValidatePasswordUseCase = ValidatePasswordUseCase(),
    private val ValidateRepeatedPassword: ValidateRepeatedPasswordUseCase = ValidateRepeatedPasswordUseCase(),
    private val ValidateTerms: ValidateTermsUseCase = ValidateTermsUseCase(),
)