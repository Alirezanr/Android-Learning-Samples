package com.example.composeapplication.cocktail_game_exercise.model

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ScoreUnitTest {
    lateinit var score: Score

    @Before
    fun setUp() {
        score = Score()
    }


    @Test
    fun whenIncrementingScore_shouldIncrementCurrentScore() {
        score.incrementScore()
        assertEquals("Current score should have been 1", 1, score.currentScore)
    }



    @Test
    fun whenIncrementingScore_belowHighScore_shouldNotIncrementHighScore() {
        score = Score(10)
        score.incrementScore()
        assertEquals("High score should have been 10", 10, score.highScore)
    }


    @Test
    fun whenIncrementingScore_shouldIncrementHighScore() {
        score.incrementScore()
        assertEquals("Current score should have been 1", 1, score.highScore)
    }
}