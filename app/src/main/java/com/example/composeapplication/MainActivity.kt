package com.example.composeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenStarted {
            val preferences = UserAuthPreferences(this@MainActivity)
            preferences.saveAuthToken("Some token to save")

            preferences.authToken.collect {
                println(it)
            }
        }

    }

}



