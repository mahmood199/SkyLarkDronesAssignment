package com.example.composedweather.data.models.response

import kotlinx.serialization.SerialName


data class CurrentUnits(
    @SerialName("apparent_temperature") val apparent_temperature: String,
    @SerialName("interval") val interval: String,
    @SerialName("is_day") val is_day: String,
    @SerialName("precipitation") val precipitation: String,
    @SerialName("rain") val rain: String,
    @SerialName("relativehumidity_2m") val relativehumidity_2m: String,
    @SerialName("showers") val showers: String,
    @SerialName("snowfall") val snowfall: String,
    @SerialName("temperature_2m") val temperature_2m: String,
    @SerialName("time") val time: String,
    @SerialName("weathercode") val weathercode: String,
    @SerialName("winddirection_10m") val winddirection_10m: String,
    @SerialName("windspeed_10m") val windspeed_10m: String
)