package com.example.composedweather.data.models.response

import kotlinx.serialization.SerialName

data class Minutely15(
    @SerialName("apparent_temperature") val apparentTemperature: List<Double>,
    @SerialName("precipitation") val precipitation: List<Double>,
    @SerialName("relativehumidity_2m") val relativeHumidity2m: List<Int>,
    @SerialName("temperature_2m") val temperature2m: List<Double>,
    @SerialName("time") val time: List<String>
)