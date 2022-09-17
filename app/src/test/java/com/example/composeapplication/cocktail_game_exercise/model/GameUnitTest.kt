package com.example.composeapplication.cocktail_game_exercise.model

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.*

class GameUnitTest {

    private val questions = listOf(
        Question(correctOption = "a", incorrectOption = "b"),
        Question(correctOption = "c", incorrectOption = "d"),
        Question(correctOption = "e", incorrectOption = "f"),
    )
    lateinit var game: Game

    @Before
    fun setUp() {
        game = Game(
            questions = questions
        )
    }

    @Test
    fun whenReachingToEndOFQuestionsList_returnNull() {
        for (i in questions.indices) {
            game.nextQuestion()
        }
        assertNull(game.nextQuestion())
    }

    @Test
    fun whenGettingNextQuestion_shouldReturnIt() {
        val returnedQuestion = game.nextQuestion()
        val expectedQuestion = questions.first()
        assertEquals(expectedQuestion, returnedQuestion)
    }

    @Test
    fun whenAnswering_shouldDelegateToQuestion() {
        // mock an object of Question class.
        val question = mock<Question>()
        // pass question object to Game constructor.
        val game = Game(listOf(question))
        // call answer method in Game class.
        game.answer(question, "OPTION")
        // verify that answer method of question object has called with "OPTION" argument.
        verify(question/*, times(1)*/).answer(eq("OPTION"))
    }

    @Test
    fun whenAnsweringCorrectly_shouldIncrementScore() {
        val question = mock<Question>()
        whenever(question.answer(anyString())).thenReturn(true)
        val score = mock<Score>()
        val game = Game(listOf(question), score)

        game.answer(question, "OPTION")

        verify(score, times(1)).incrementScore()
    }


    @Test
    fun whenAnsweringIncorrectly_shouldNotIncrementScore() {
        val question = mock<Question>()
        whenever(question.answer(anyString())).thenReturn(false)

        val score = mock<Score>()
        val game = Game(listOf(question))
        game.answer(question, "OPTION")
        verify(score, never()).incrementScore()
    }
}