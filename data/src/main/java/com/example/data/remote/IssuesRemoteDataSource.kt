package com.example.data.remote

import com.example.core_network.NetworkResult
import com.example.core_network.ResponseProcessor
import com.example.data.model.response.Issue
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import java.io.IOException
import javax.inject.Inject

class IssuesRemoteDataSource @Inject constructor(
    private val httpClient: HttpClient,
    private val responseProcessor: ResponseProcessor,
    private val gson: Gson
) {

    suspend fun getAllIssues(issueUrl: String): NetworkResult<List<Issue>> {
        return try {
            val response = httpClient.get(issueUrl)

            val result =
                responseProcessor.getResultFromListResponse<Issue>(gson, response)
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