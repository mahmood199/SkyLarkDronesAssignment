package com.example.composedweather.data.repository.contract

import com.example.composedweather.data.preference.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {

    fun getUserPreferences(): Flow<UserPreferences>

    suspend fun setTemperatureUnit(temperatureUnit: String)

    suspend fun setUserLocation(latitude: Double, longitude: Double, location: String)

}