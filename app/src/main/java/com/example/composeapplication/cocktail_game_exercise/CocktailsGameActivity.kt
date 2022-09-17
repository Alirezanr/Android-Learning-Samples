package com.example.composeapplication.cocktail_game_exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.composeapplication.databinding.ActivityGameBinding

class CocktailsGameActivity : AppCompatActivity() {

  private lateinit var binding: ActivityGameBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityGameBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }
}
