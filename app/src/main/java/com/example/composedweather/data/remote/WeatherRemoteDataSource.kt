package com.example.composedweather.data.remote

import com.example.composedweather.BuildConfig
import com.example.composedweather.core.remote.NetworkResult
import com.example.composedweather.core.remote.WeatherClient
import com.example.composedweather.core.remote.ResponseProcessor
import com.example.composedweather.data.models.request.Constants
import com.example.composedweather.data.models.request.WeatherDataRequest
import com.example.composedweather.data.models.response.WeatherResponse
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    @WeatherClient private val httpClient: HttpClient,
    private val responseProcessor: ResponseProcessor,
    private val gson: Gson
) {

    suspend fun getWeatherForecast(request: WeatherDataRequest): NetworkResult<WeatherResponse> {
        return try {
            val response = httpClient.get(BuildConfig.OPEN_METEO_BASE_URL + "forecast") {
                parameter("latitude", 52.52)
                parameter("longitude", 13.41)
                parameter("forecast_days", 1)
                if(request.currentDayRequested) {
                    parameter("current", request.params)
                }
                parameter(Constants.TEMPERATURE_UNIT, request.temperatureUnit)
                parameter("hourly", "temperature_2m")
                parameter("hourly", "relativehumidity_2m")
                parameter("hourly", "dewpoint_2m")
            }
            val result = responseProcessor.getResultFromResponse<WeatherResponse>(gson, response)
            result
        } catch (e: Exception) {
            NetworkResult.Exception(Throwable("Something went wrong"))
        }
    }

}