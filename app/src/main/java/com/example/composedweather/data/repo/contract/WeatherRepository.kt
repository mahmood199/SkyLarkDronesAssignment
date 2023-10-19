package com.example.composedweather.data.repo.contract

import com.example.composedweather.core.remote.NetworkResult
import com.example.composedweather.data.models.request.WeatherDataRequest
import com.example.composedweather.data.models.response.WeatherResponse

interface WeatherRepository {

    suspend fun getInfo(weatherDataRequest: WeatherDataRequest): NetworkResult<WeatherResponse>

}