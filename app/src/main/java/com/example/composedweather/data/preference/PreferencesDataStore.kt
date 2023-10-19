package com.example.composedweather.data.preference

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("PreferencesDataStore", exception.toString())
                emit(emptyPreferences())
            } else {
                throw Exception()
            }
        }
        .map { preferences ->
            val latitude = preferences[PreferencesKeys.LATITUDE] ?: 0.0
            val longitude = preferences[PreferencesKeys.LONGITUDE] ?: 0.0

            UserPreferences(latitude = latitude, longitude = longitude)
        }

    suspend fun setUserLocation(latitude: Double, longitude: Double) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LATITUDE] = latitude
            preferences[PreferencesKeys.LONGITUDE] = longitude
        }
    }

    private object PreferencesKeys {
        val LATITUDE = doublePreferencesKey("latitude")
        val LONGITUDE = doublePreferencesKey("longitude")
    }

    companion object {
        const val USER_PREFERENCES_NAME = "user_preferences"
    }

}