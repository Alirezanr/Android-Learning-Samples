package com.example.composeapplication.cocktail_game_exercise

import android.content.SharedPreferences
import com.example.composeapplication.cocktail_game_exercise.common.network.CocktailsApi

private const val HIGH_SCORE_KEY = "HIGH_SCORE_KEY"

class CocktailsRepositoryImpl(
    private val api: CocktailsApi,
    private val sharedPreferences: SharedPreferences
) : CocktailsRepository {

    override fun saveHighScore(score: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(HIGH_SCORE_KEY, score)
        editor.apply()
    }

    override fun getHighScore(): Int = sharedPreferences.getInt(HIGH_SCORE_KEY,0)
}