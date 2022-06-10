package com.example.composeapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.composeapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fakeData = List(15) { i ->
            CharacterModel(
                name = "name $i",
                role = "some role",
                orderOfThePhoenix = i % 2 == 0
            )
        }

        binding.characterRecyclerview.apply {
            adapter = CharacterAdapter {
                Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_SHORT).show()
            }.also {
                it.updateDataSet(fakeData)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
