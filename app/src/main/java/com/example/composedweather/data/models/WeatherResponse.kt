package com.example.composedweather.data.models

import kotlinx.serialization.SerialName

data class WeatherResponse(
    @SerialName("elevation") val elevation: Int,
    @SerialName("generationtime_ms") val generationTimeMs: Double,
    @SerialName("hourly") val hourly: Hourly,
    @SerialName("hourly_units") val hourlyUnits: HourlyUnits,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("minutely_15") val minutely15: Minutely15,
    @SerialName("minutely_15_units") val minutely15Units: Minutely15Units,
    @SerialName("timezone") val timezone: String,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds") val utcOffsetSeconds: Int
)