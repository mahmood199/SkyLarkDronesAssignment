package com.example.composedweather.core

import io.ktor.client.plugins.compression.ContentEncoding


fun ContentEncoding.Config.brotli(quality: Float? = null) {
    customEncoder(BrotliEncoder, quality)
}
