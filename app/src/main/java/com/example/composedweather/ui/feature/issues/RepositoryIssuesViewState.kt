package com.example.composedweather.ui.feature.issues

data class RepositoryIssuesViewState(
    val isLoading: Boolean = false,
    var error: String? = null,
)