package com.example.composedweather.data.models.response

import kotlinx.serialization.SerialName

data class Minutely15Units(
    @SerialName("apparent_temperature") val apparentTemperature: String,
    @SerialName("precipitation") val precipitation: String,
    @SerialName("relativehumidity_2m") val relativeHumidity2m: String,
    @SerialName("temperature_2m") val temperature2m: String,
    @SerialName("time") val time: String
)