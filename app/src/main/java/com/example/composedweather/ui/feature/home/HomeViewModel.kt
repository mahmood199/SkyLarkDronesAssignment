package com.example.composedweather.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedweather.connection.NetworkConnectivityObserver
import com.example.composedweather.core.remote.NetworkResult
import com.example.composedweather.data.repo.contract.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val networkConnectivityObserver: NetworkConnectivityObserver,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            networkConnectivityObserver.networkState.collectLatest {
                _state.value = _state.value.copy(isConnected = it)
            }
        }

        getInfo()
    }

    private fun getInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(isLoading = true)
            when (val result = repository.getInfo(state.value.weatherDataRequest)) {
                is NetworkResult.Exception -> {
                    _state.value = _state.value.copy(error = result.e.message)
                    _state.value = _state.value.copy(isLoading = false)
                }
                is NetworkResult.RedirectError -> {
                    _state.value = _state.value.copy(error = result.e.message)
                    _state.value = _state.value.copy(isLoading = false)
                }
                is NetworkResult.ServerError -> {
                    _state.value = _state.value.copy(error = result.e.message)
                    _state.value = _state.value.copy(isLoading = false)
                }
                is NetworkResult.Success -> {
                    _state.value = _state.value.copy(isLoading = false)

                }
                is NetworkResult.UnAuthorised -> {
                    _state.value = _state.value.copy(error = result.e.message)
                    _state.value = _state.value.copy(isLoading = false)
                }
            }
        }
    }


}