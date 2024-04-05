package com.example.composeapplication

import android.content.Context
import kotlinx.collections.immutable.PersistentList
import androidx.datastore.dataStore
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

val Context.appSettingsDataStore by dataStore(fileName = "app-seetings.json", AppSettingsSerializer)

@Serializable
data class AppSettings(
    val language: Language = Language.English,
    val knownLocations: PersistentList<Location> = persistentListOf()
)

@Serializable
data class Location(
    val lat: Double,
    val lng: Double
)

enum class Language {
    English,
    Persian,
    Turkish
}