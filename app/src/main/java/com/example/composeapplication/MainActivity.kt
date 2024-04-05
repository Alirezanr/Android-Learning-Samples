package com.example.composeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenStarted {
            //read from data store
            appSettingsDataStore.data.collectLatest {

            }
            val preferences = UserAuthPreferences(this@MainActivity)
            preferences.saveAuthToken("Some token to save")

            preferences.authToken.collect {
                println(it)
            }
        }


    }

    private suspend fun setData(lang: Language, location: Location) {
        //write to data store:
        appSettingsDataStore.updateData {
            it.copy(
                language = lang,
                knownLocations = it.knownLocations.mutate {
                    it.add(location)
                }
            )
        }
    }

}



