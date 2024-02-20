package com.example.composedweather.ui.feature.issues

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network.NetworkResult
import com.example.data.model.response.Issue
import com.example.domain.issues.GetIssuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesIssuesViewModel @Inject constructor(
    private val getIssuesUseCase: GetIssuesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(RepositoryIssuesViewState())
    val state = _state.asStateFlow()

    val issuesUrl = savedStateHandle.get<String>("issues_id") ?: ""

    var issues = mutableStateListOf<Issue>()

    init {
        getAllIssues()
    }

    private fun getAllIssues() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getIssuesUseCase.getAllIssues(issuesUrl)) {
                is NetworkResult.Success -> {
                    issues.clear()
                    issues.addAll(result.data)
                }

                is NetworkResult.Exception -> handleError(result.e)
                is NetworkResult.RedirectError -> handleError(result.e)
                is NetworkResult.ServerError -> handleError(result.e)
                is NetworkResult.UnAuthorised -> handleError(result.e)
            }
        }
    }

    private fun handleError(e: Throwable) {
        _state.value = _state.value.copy(error = e.message)
        _state.value = _state.value.copy(isLoading = false)
    }


}