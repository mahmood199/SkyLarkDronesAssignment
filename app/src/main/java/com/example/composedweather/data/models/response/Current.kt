package com.example.composedweather.data.models.response

import kotlinx.serialization.SerialName

data class Current(
    @SerialName("apparent_temperature") val apparent_temperature: Double,
    @SerialName("interval") val interval: Int,
    @SerialName("is_day") val is_day: Int,
    @SerialName("precipitation") val precipitation: Int,
    @SerialName("rain") val rain: Int,
    @SerialName("relativehumidity_2m") val relativehumidity_2m: Int,
    @SerialName("showers") val showers: Int,
    @SerialName("snowfall") val snowfall: Int,
    @SerialName("temperature_2m") val temperature_2m: Double,
    @SerialName("time") val time: String,
    @SerialName("weathercode") val weathercode: Int,
    @SerialName("winddirection_10m") val winddirection_10m: Int,
    @SerialName("windspeed_10m") val windspeed_10m: Double
)