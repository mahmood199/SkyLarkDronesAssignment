package com.example.composedweather.data.repo.contract

import com.example.composedweather.core.NetworkResult
import com.example.composedweather.data.models.WeatherResponse

interface WeatherRepository {

    suspend fun getInfo(): NetworkResult<WeatherResponse>

}