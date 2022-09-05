package com.example.composeapplication.cocktail_game_exercise.model

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

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
    fun whenIncrementingScore_shouldIncrementCurrentScore() {
        game.incrementScore()
        assertEquals("Current score should have been 1", 1, game.currentScore)
    }

    @Test
    fun whenIncrementingScore_belowHighScore_shouldNotIncrementHighScore() {
        val game = Game(highScore = 10)
        game.incrementScore()
        assertEquals("High score should have been 10", 10, game.highScore)
    }

    @Test
    fun whenIncrementingScore_shouldIncrementHighScore() {
        game.incrementScore()
        assertEquals("Current score should have been 1", 1, game.currentScore)
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
        val expectedQuestion = game.questions.first()
        assertEquals(expectedQuestion, returnedQuestion)
    }
}