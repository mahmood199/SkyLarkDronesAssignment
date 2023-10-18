package com.example.composedweather.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedweather.core.NetworkResult
import com.example.composedweather.data.repo.contract.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    init {
        getInfo()
    }

    private fun getInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getInfo()
            val x = 13
            when (result) {
                is NetworkResult.Exception -> {}
                is NetworkResult.RedirectError -> {}
                is NetworkResult.ServerError -> {}
                is NetworkResult.Success -> {

                }
                is NetworkResult.UnAuthorised -> {}
            }
        }
    }


}