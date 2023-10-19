package com.example.composedweather.ui.feature.search

data class SearchLocationViewState(
    val isLoading: Boolean = false,
    var error: String? = null,
    val isInSearchMode: Boolean = false,
    val isConnected: Boolean = false
)
