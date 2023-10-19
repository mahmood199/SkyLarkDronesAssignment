package com.example.composedweather.data.repo.implementation

import com.example.composedweather.core.remote.NetworkResult
import com.example.composedweather.data.models.request.WeatherDataRequest
import com.example.composedweather.data.models.response.WeatherResponse
import com.example.composedweather.data.remote.WeatherRemoteDataSource
import com.example.composedweather.data.repo.contract.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource
): WeatherRepository {

    override suspend fun getInfo(weatherDataRequest: WeatherDataRequest): NetworkResult<WeatherResponse> {
        return remoteDataSource.getWeatherForecast(weatherDataRequest)
    }

}