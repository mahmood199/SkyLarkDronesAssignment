package com.example.composedweather.data.models.response

import kotlinx.serialization.SerialName

data class HourlyUnits(
    @SerialName("dewpoint_2m") val dewpoint2m: String,
    @SerialName("relativehumidity_2m") val relativeHumidity2m: String,
    @SerialName("temperature_2m") val temperature2m: String,
    @SerialName("time") val time: String
)