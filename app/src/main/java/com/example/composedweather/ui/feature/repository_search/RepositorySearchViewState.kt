package com.example.composedweather.ui.feature.repository_search

data class RepositorySearchViewState(
    val isLoading: Boolean = false,
    var error: String? = null,
    val isInSearchMode: Boolean = false,
    val isConnected: Boolean = false
)