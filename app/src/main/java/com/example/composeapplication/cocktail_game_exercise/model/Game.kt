package com.example.composeapplication.cocktail_game_exercise.model

class Game(
    val questions: List<Question> = emptyList(),
    highScore: Int = 0
) {
    var currentScore = 0
        private set
    var highScore = highScore
        private set

    var lastReturnedQuestionIndex = -1

    fun incrementScore() {
        currentScore++
        if (currentScore > highScore) {
            highScore++
        }
    }

    fun nextQuestion(): Question? {
        lastReturnedQuestionIndex++

        if (lastReturnedQuestionIndex >= questions.size)
            return null

        val nextQuestion = questions[lastReturnedQuestionIndex]
        return nextQuestion
    }
}