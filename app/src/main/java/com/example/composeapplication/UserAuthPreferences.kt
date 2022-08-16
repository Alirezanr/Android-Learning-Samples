package com.example.composeapplication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserAuthPreferences constructor(private val context: Context) {
    private val AUTH_KEY = stringPreferencesKey("auth_key")

    val authToken: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[AUTH_KEY]
    }

    suspend fun saveAuthToken(authToken: String) {
        context.dataStore.edit { preferences ->
            preferences[AUTH_KEY] = authToken
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}