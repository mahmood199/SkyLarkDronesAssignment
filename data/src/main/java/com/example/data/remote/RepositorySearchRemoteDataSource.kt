package com.example.data.remote

import com.example.core_network.NetworkResult
import com.example.core_network.ResponseProcessor
import com.example.data.model.response.RepositoryListResponse
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.io.IOException
import javax.inject.Inject

class RepositorySearchRemoteDataSource @Inject constructor(
    private val httpClient: HttpClient,
    private val responseProcessor: ResponseProcessor,
    private val gson: Gson
) {

    suspend fun getRepositories(query: String): NetworkResult<RepositoryListResponse> {
        return try {
            val response = httpClient.get("https://api.github.com/search/repositories") {
                parameter("q", query)
            }

            val result =
                responseProcessor.getResultFromResponse<RepositoryListResponse>(gson, response)
            result
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is IOException) {
                NetworkResult.Exception(Throwable("Please your internet connection"))
            } else {
                NetworkResult.Exception(Throwable("Something went wrong"))
            }
        }
    }

}