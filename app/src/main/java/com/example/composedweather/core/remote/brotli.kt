package com.example.composedweather.core.remote

import io.ktor.client.plugins.compression.ContentEncoding


fun ContentEncoding.Config.brotli(quality: Float? = null) {
    customEncoder(BrotliEncoder, quality)
}
