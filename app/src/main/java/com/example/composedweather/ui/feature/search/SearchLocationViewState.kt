package com.example.composedweather.ui.feature.search

data class SearchLocationViewState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val landed: Boolean = true
)
