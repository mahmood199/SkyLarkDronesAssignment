package com.example.composedweather.data.models

import kotlinx.serialization.SerialName

data class Hourly(
    @SerialName("dewpoint_2m") val dewpoint2m: List<Double>,
    @SerialName("relativehumidity_2m") val relativeHumidity2m: List<Int>,
    @SerialName("temperature_2m") val temperature2m: List<Double>,
    @SerialName("time") val time: List<String>
)