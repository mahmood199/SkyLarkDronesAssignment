package com.example.composedweather.data.preference

data class UserPreferences(
    val latitude: Double,
    val longitude: Double,
    val temperatureUnit: String,
    val location: String
)