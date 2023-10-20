package com.example.composedweather.data.models.response

data class HourlyForecast(
    val relativeHumidity: Int,
    val relativeHumidityUnit: String,
    val temperature: Double,
    val temperatureUnit: String,
    val time: String,
    val timeUnit: String,
)