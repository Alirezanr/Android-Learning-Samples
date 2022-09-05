package com.example.composeapplication.cocktail_game_exercise.model

class Question(
    val correctOption: String,
    val incorrectOption: String
) {
    var answeredOption: String? = null
        private set

    val isAnsweredCorrectly: Boolean
        get() = answeredOption == correctOption

    fun answer(answer: String): Boolean {
        if (answer != correctOption && answer != incorrectOption)
            throw IllegalArgumentException("Passed argument is illegal.")
        answeredOption = answer
        return isAnsweredCorrectly
    }

    fun getOptions(sort: (List<String>) -> List<String> = { it.shuffled() }): List<String> {
        return sort(listOf(correctOption, incorrectOption))
    }
}