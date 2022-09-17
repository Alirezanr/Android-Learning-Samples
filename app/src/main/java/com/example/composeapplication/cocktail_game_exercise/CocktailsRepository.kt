package com.example.composeapplication.cocktail_game_exercise

interface CocktailsRepository {
    fun saveHighScore(score: Int)
    fun getHighScore() :Int
}