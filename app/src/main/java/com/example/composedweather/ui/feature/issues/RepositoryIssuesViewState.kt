package com.example.composedweather.ui.feature.issues

import com.example.data.model.response.Item

data class RepositoryIssuesViewState(
    val toolbarText: String = "",
    val isLoading: Boolean = false,
    var error: String? = null,
    val item : Item? = null
)