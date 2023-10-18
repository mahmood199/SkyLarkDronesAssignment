package com.example.composedweather.data.repo.implementation

import com.example.composedweather.core.NetworkResult
import com.example.composedweather.data.models.WeatherResponse
import com.example.composedweather.data.remote.WeatherRemoteDataSource
import com.example.composedweather.data.repo.contract.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource
): WeatherRepository {

    override suspend fun getInfo(): NetworkResult<WeatherResponse> {
        return remoteDataSource.getWeatherForecast()
    }

}