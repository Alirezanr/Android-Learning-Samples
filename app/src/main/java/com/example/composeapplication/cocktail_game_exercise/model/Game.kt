package com.example.composeapplication.cocktail_game_exercise.model

class Game(
    private val questions: List<Question>,
    val score: Score = Score(0)
) {

    var lastReturnedQuestionIndex = -1

    fun nextQuestion(): Question? {
        if (lastReturnedQuestionIndex + 1 < questions.size) {
            lastReturnedQuestionIndex++
            return questions[lastReturnedQuestionIndex]
        }
        return null
    }

    fun answer(question: Question, option: String) {
        if (question.answer(option))
            score.incrementScore()
    }
}