package com.example.composeapplication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composeapplication.domain.use_case.InputValidationsUseCases

class MainViewModel(
    private val InputValidations: InputValidationsUseCases = InputValidationsUseCases()
) : ViewModel() {
    var state by mutableStateOf(RegistrationFormState())
}