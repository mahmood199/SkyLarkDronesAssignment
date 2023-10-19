package com.example.composedweather.data.models.request


data class WeatherDataRequest(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val params: List<String> = emptyList(),
    val temperatureUnit: String = ApiConstants.CELSIUS,
    val forecastDays: Int = 5,
    val pastDays: Int = 0,
    val startDate: String = "",
    val endDate: String = "",
    val currentDayRequested: Boolean = false
)
