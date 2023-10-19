package com.example.composedweather.data.repository.contract

import com.example.composedweather.core.remote.NetworkResult
import com.example.composedweather.data.models.response.LocationResponseItem

interface LocationRepository {

    suspend fun searchByName(location: String): NetworkResult<List<LocationResponseItem>>

}