package com.example.composedweather.ui.feature.search

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedweather.core.remote.NetworkResult
import com.example.composedweather.data.models.response.LocationResponseItem
import com.example.composedweather.data.repository.contract.LocationRepository
import com.example.composedweather.data.repository.contract.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class SearchLocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchLocationViewState())
    val state = _state.asStateFlow()

    private val _query = MutableStateFlow("")
    val uiQuery = _query.asStateFlow()

    var searchResults = mutableStateListOf<LocationResponseItem>()

    fun updateSearchQuery(it: String) {
        _query.value = it
        if(it.isEmpty()) {
            searchResults.clear()
        }
    }

    fun searchLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(isLoading = true)
            when (val result = locationRepository.searchByName(_query.value)) {
                is NetworkResult.Exception -> handleError(result.e)
                is NetworkResult.RedirectError -> handleError(result.e)
                is NetworkResult.ServerError -> handleError(result.e)
                is NetworkResult.UnAuthorised -> handleError(result.e)

                is NetworkResult.Success -> {
                    searchResults.clear()
                    searchResults.addAll(result.data)
                }
            }
            _state.value = _state.value.copy(isLoading = false, landed = false)
        }
    }

    private fun handleError(e: Throwable) {
        _state.value = _state.value.copy(error = e.message)
        _state.value = _state.value.copy(isLoading = false)
    }

    fun setLocationCoordinates(locationResponseItem: LocationResponseItem) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferenceRepository.setUserLocation(
                latitude = locationResponseItem.lat.take(6).toDoubleOrNull() ?: 0.0,
                longitude = locationResponseItem.lon.take(6).toDoubleOrNull() ?: 0.0,
                location = locationResponseItem.displayName
            )
        }
    }


}