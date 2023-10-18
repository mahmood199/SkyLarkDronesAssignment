package com.example.composedweather.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedweather.ui.theme.ComposedWeatherTheme

@Composable
fun DetailUI(
    viewModel: DetailViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

    }
}

@Preview
@Composable
fun HomeUIPreview() {
    ComposedWeatherTheme {
        DetailUI()
    }
}