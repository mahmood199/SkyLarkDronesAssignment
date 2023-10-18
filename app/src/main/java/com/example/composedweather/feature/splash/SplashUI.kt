package com.example.composedweather.feature.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 1000

@Composable
fun SplashUI(
    navigateToHome: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val currentOnTimeout by rememberUpdatedState(navigateToHome)

    BackHandler {}

    LaunchedEffect(Unit) {
        delay(timeMillis = SplashWaitTime)
        currentOnTimeout()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {


    }
}

@Preview
@Composable
fun SplashUIPreview() {
    ComposedWeatherTheme {
        SplashUI(
            navigateToHome = {
            }
        )
    }
}