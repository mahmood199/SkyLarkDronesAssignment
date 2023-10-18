package com.example.composedweather.ui.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedweather.ui.common.ComposedWeatherAppBarUI
import com.example.composedweather.ui.theme.ComposedWeatherTheme

@Composable
fun DetailUI(
    onBackPressed: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        ComposedWeatherAppBarUI(
            title = "Detail Screen",
            backButtonIcon = Icons.Rounded.ArrowBack,
            onBackPressed
        )
    }
}

@Preview
@Composable
fun HomeUIPreview() {
    ComposedWeatherTheme {
        DetailUI(onBackPressed = {})
    }
}