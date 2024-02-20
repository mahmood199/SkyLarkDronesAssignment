package com.example.composedweather.ui.feature.issues

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RepositoriesIssuesViewModel @Inject constructor(

    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(RepositoryIssuesViewState())
    val state = _state.asStateFlow()

    init {

    }


}