package com.example.composeapplication.cocktail_game_exercise.common.repository

import android.content.SharedPreferences
import com.example.composeapplication.cocktail_game_exercise.CocktailsRepository
import com.example.composeapplication.cocktail_game_exercise.CocktailsRepositoryImpl
import com.example.composeapplication.cocktail_game_exercise.common.network.CocktailsApi
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class RepositoryUnitTests {

    lateinit var api: CocktailsApi
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    lateinit var sharedPreferences: SharedPreferences
    lateinit var repository: CocktailsRepositoryImpl

    @Before
    fun setup() {
        api = mock()
        sharedPreferencesEditor = mock()
        sharedPreferences = mock()
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        repository = CocktailsRepositoryImpl(api, sharedPreferences)
    }

    @Test
    fun saveScore_shouldSaveToSharedPreferences() {
        val score = 100
        repository.saveHighScore(score)
        // 3
        inOrder(sharedPreferencesEditor) {
            // 4
            verify(sharedPreferencesEditor).putInt(any(), eq(score))
            verify(sharedPreferencesEditor).apply()
        }
    }

    @Test
    fun getScore_shouldGetFromSharedPreferences() {
        repository.getHighScore()
        verify(sharedPreferences).getInt(any(), any())
    }
}