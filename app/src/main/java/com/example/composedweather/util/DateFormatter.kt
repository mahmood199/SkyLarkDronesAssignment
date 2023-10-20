package com.example.composedweather.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun String.formatToDMMMY(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("EEEE, d MMM")

    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        "Error"
    }
}