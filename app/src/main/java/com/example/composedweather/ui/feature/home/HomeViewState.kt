package com.example.composedweather.ui.feature.home

data class HomeViewState(
    var isLoading: Boolean = false,
    var error: String? = null,
    var isConnected: Boolean = false
)
