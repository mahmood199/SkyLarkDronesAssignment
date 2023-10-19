package com.example.composedweather.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedweather.connection.NetworkConnectivityObserver
import com.example.composedweather.core.remote.NetworkResult
import com.example.composedweather.data.repository.contract.UserPreferenceRepository
import com.example.composedweather.data.repository.contract.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkConnectivityObserver: NetworkConnectivityObserver,
    private val weatherRepository: WeatherRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            networkConnectivityObserver.networkState.collectLatest {
                _state.value = _state.value.copy(isConnected = it)
            }
        }

        viewModelScope.launch {
            userPreferenceRepository.getUserPreferences().distinctUntilChanged().collectLatest {
                val request = _state.value.weatherDataRequest

                _state.value = _state.value.copy(
                    weatherDataRequest = request.copy(
                        latitude = it.latitude,
                        longitude = it.longitude,
                        temperatureUnit = it.temperatureUnit
                    )
                )

                getInfo()
            }
        }
    }

    private fun getInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(isLoading = true)
            when (val result = weatherRepository.getInfo(state.value.weatherDataRequest)) {
                is NetworkResult.Exception -> {
                    handleError(result.e)
                }

                is NetworkResult.RedirectError -> {
                    handleError(result.e)
                }

                is NetworkResult.ServerError -> {
                    handleError(result.e)
                }


                is NetworkResult.UnAuthorised -> {
                    handleError(result.e)
                }

                is NetworkResult.Success -> {
                    _state.value = _state.value.copy(isLoading = false)

                }
            }
        }
    }

    private fun handleError(e: Throwable) {
        _state.value = _state.value.copy(error = e.message)
        _state.value = _state.value.copy(isLoading = false)
    }

    fun modifyContent(state: HomeViewState) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferenceRepository.setTemperatureUnit(state.weatherDataRequest.temperatureUnit)
        }
    }


}