package com.example.composedweather.core.remote

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class ResponseProcessor @Inject constructor() {

    inline fun <reified T> deserializeToClass(gson: Gson, response: String): T {
        return gson.fromJson(response, T::class.java)
    }

    suspend inline fun <reified R : Any> getResultFromResponse(
        gson: Gson,
        output: HttpResponse
    ): NetworkResult<R> {
        return when (val code = output.status.value) {
            in 200..201 -> NetworkResult.Success(
                data = deserializeToClass(gson, output.body<String>())
            )

            in 300..399 -> NetworkResult.RedirectError(
                e = Exception("Error Redirect"),
                code = code,
                message = "Error Redirect"
            )

            in 400..499 -> NetworkResult.UnAuthorised(
                e = Exception("Error Authentication"),
                code = code,
                message = "UnAuthorised Access"
            )

            in 500..599 -> NetworkResult.ServerError(
                e = Exception("Server Error"),
                code = code,
                message = "Server Error"
            )

            else -> {
                NetworkResult.Exception(Exception())
            }
        }
    }

    suspend inline fun <reified R : Any> getResultFromListResponse(
        gson: Gson,
        output: HttpResponse
    ): NetworkResult<List<R>> {
        return when (val code = output.status.value) {
            in 200..201 -> {
                Log.d("ResponseProcessor1", output.body())
                val list = deserializeToList<R>(gson, output.body<String>().toString())
                Log.d("ResponseProcessor2", list.toString())
                NetworkResult.Success(
                    data = list
                )
            }

            in 300..399 -> NetworkResult.RedirectError(
                e = Exception("Error Redirect"),
                code = code,
                message = "Error Redirect"
            )

            in 400..499 -> NetworkResult.UnAuthorised(
                e = Exception("Error Authentication"),
                code = code,
                message = "UnAuthorised Access"
            )

            in 500..599 -> NetworkResult.ServerError(
                e = Exception("Server Error"),
                code = code,
                message = "Server Error"
            )

            else -> {
                NetworkResult.Exception(Exception())
            }
        }
    }

    inline fun <reified R> deserializeToList(gson: Gson, body: String): List<R> {
        return try {
            Log.d("ResponseParser3", "Parsing the list")
            val listType = object : TypeToken<List<R>>() {}.type
            Log.d("ResponseParser4", "Parsing the list")
            val result = gson.fromJson<List<R>>(body, listType)
            Log.d("ResponseParser5", "Parsing the list")
            result
        } catch (e: Exception) {
            Log.d("ResponseParser6", "Parsing the list")
            e.printStackTrace()
            emptyList()
        }
    }


}