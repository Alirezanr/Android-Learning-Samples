package com.example.composeapplication.cocktail_game_exercise.model

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException

class QuestionUnitTests {

    private lateinit var question: Question
    private val correctAnswer = "CORRECT"
    private val incorrectAnswer = "INCORRECT"

    @Before
    fun setup() {
        question = Question(correctAnswer, incorrectAnswer)
    }

    @Test
    fun whenCreatingQuestion_shouldNotHaveAnsweredOption() {
        assertNull(question.answeredOption)
    }

    @Test
    fun whenAnswering_shouldHaveAnswer() {
        val answer = correctAnswer
        question.answer(answer)
        assertEquals(answer, question.answeredOption)
    }

    @Test
    fun whenAnswering_withCorrectAnswer_shouldReturnTrue() {
        val result = question.answer(correctAnswer)
        assertTrue("Answer is not correct", result)
    }

    @Test
    fun whenAnswering_withIncorrectAnswer_shouldReturnFalse() {
        val result = question.answer(incorrectAnswer)
        assertFalse("Expected to be false.", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun whenAnswering_withInvalidAnswer_shouldThrowException() {
        val invalidAnswer = "Invalid"
        question.answer(invalidAnswer)
    }


    @Test
    fun whenCreatingQuestion_shouldReturnOptionsWithCustomSort() {
        val questions = question.getOptions {
            it.reversed()
        }

        assertEquals(listOf(correctAnswer,incorrectAnswer ).reversed(), questions)
    }
}