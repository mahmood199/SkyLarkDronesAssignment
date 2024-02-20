package com.example.composedweather.ui.feature.repository_search

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_network.NetworkResult
import com.example.data.model.response.Item
import com.example.domain.repositories.SortRepositoriesByStarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositorySearchViewModel @Inject constructor(
    private val repositoriesByStarsUseCase: SortRepositoriesByStarsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RepositorySearchViewState())
    val state = _state.asStateFlow()

    private val _query = MutableStateFlow("")
    val uiQuery = _query.asStateFlow()

    var searchResults = mutableStateListOf<Item>()

    fun updateSearchQuery(it: String) {
        _query.value = it
        if (it.isEmpty()) {
            searchResults.clear()
        }
        _state.value = _state.value.copy(isInSearchMode = false)
    }

    fun searchLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(isLoading = true)
            when (val result = repositoriesByStarsUseCase.getProjects(_query.value)) {
                is NetworkResult.Exception -> handleError(result.e)
                is NetworkResult.RedirectError -> handleError(result.e)
                is NetworkResult.ServerError -> handleError(result.e)
                is NetworkResult.UnAuthorised -> handleError(result.e)

                is NetworkResult.Success -> {
                    searchResults.clear()
                    searchResults.addAll(result.data.items)
                }
            }
            _state.value = _state.value.copy(isLoading = false, isInSearchMode = true)
        }
    }

    private fun handleError(e: Throwable) {
        _state.value = _state.value.copy(error = e.message)
        _state.value = _state.value.copy(isLoading = false)
    }


    fun resetError() {
        _state.value = _state.value.copy(error = null)
    }

}