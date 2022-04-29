package com.example.composeapplication.domain.use_case

import com.example.composeapplication.domain.util.Constants
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidateEmailUseCaseTest {

    private lateinit var validateEmailUseCase: ValidateEmailUseCase

    private val EMPTY_EMAIL_ADDRESS = ""
    private val INVALID_EMAIL_ADDRESS = "abc@sdf"
    private val VALID_EMAIL_ADDRESS = "abc@sdf.ss"

    @Before
    fun setUp() {
        validateEmailUseCase = ValidateEmailUseCase()
    }

    @Test
    fun `Empty email, returns false`() {
        val result = validateEmailUseCase(EMPTY_EMAIL_ADDRESS)
        assertFalse(result.successful)
    }

    @Test
    fun `Empty email error message assertion`() {
        val result = validateEmailUseCase(EMPTY_EMAIL_ADDRESS)
        assertEquals(result.errorMessage, Constants.BLANK_EMAIL_ADDRESS_ERROR)
    }

    @Test
    fun `Invalid email address, returns false`() {
        val result = validateEmailUseCase(INVALID_EMAIL_ADDRESS)
        assertFalse(result.successful)
    }

    @Test
    fun `Invalid email error assertion`() {
        val result = validateEmailUseCase(INVALID_EMAIL_ADDRESS)
        assertEquals(result.errorMessage, Constants.INVALID_EMAIL_ADDRESS_ERROR)
    }

    @Test
    fun `Valid email, returns true`() {
        val result = validateEmailUseCase(VALID_EMAIL_ADDRESS)
        assertTrue(result.successful)
    }

    @Test
    fun `Valid email message, returns null`() {
        val result = validateEmailUseCase(VALID_EMAIL_ADDRESS)
        assertEquals(result.errorMessage, null)
    }
}