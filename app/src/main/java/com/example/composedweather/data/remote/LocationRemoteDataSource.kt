package com.example.composedweather.data.remote

import android.util.Log
import com.example.composedweather.BuildConfig
import com.example.composedweather.core.remote.LocationClient
import com.example.composedweather.core.remote.NetworkResult
import com.example.composedweather.core.remote.ResponseProcessor
import com.example.composedweather.data.models.response.LocationResponseItem
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    @LocationClient private val client: HttpClient,
    private val responseProcessor: ResponseProcessor,
    private val gson: Gson
) {

    suspend fun getLocations(location: String): NetworkResult<List<LocationResponseItem>> {
        return try {
            val response = client.get(BuildConfig.NOMINATIM_BASE_URL + "search") {
                parameter("q", location)
                parameter("format", "jsonv2")
                parameter("limit", 10)
            }
            Log.d("LocationDataSource", response.body<String>().toString())

            val result = responseProcessor.getResultFromListResponse<LocationResponseItem>(gson, response)
            result
        } catch (e: Exception) {
            NetworkResult.Exception(Throwable("Something went wrong"))
        }
    }



}