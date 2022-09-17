package com.example.composeapplication.cocktail_game_exercise.model

class Score(highScore: Int = 0) {
    var currentScore = 0
        private set
    var highScore = highScore
        private set

    fun incrementScore() {
        currentScore++
        if (currentScore > highScore) {
            highScore++
        }
    }
}