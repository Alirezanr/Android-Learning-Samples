package com.example.composeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first


class MainActivity : AppCompatActivity() {
    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenStarted {
            write(5)
            println("saved_number = ${read()}")
        }

    }

    //Write to a Preferences DataStore
    private suspend fun write(num: Int) {
        dataStore.edit { settings ->
            settings[EXAMPLE_COUNTER] = num
        }
    }

    private suspend fun read(): Int {
        val preferences = dataStore.data.first()
        return preferences[EXAMPLE_COUNTER] ?: -1
    }
}



