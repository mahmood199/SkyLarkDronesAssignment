package com.example.composedweather.ui.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedweather.data.models.request.Constants
import com.example.composedweather.ui.common.ComposedWeatherAppBarUI
import com.example.composedweather.ui.common.ContentLoaderUI
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import kotlinx.coroutines.delay

@Composable
fun HomeUI(
    onBackPressed: () -> Unit,
    navigateAhead: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    var isShowing by remember {
        mutableStateOf(false)
    }

    val color by animateColorAsState(
        targetValue = if (state.isConnected) Color.Green else Color.DarkGray,
        label = "Bottom UI BG color"
    )

    LaunchedEffect(state.isConnected) {
        isShowing = true
        delay(2000)
        isShowing = false
    }

    Scaffold(
        topBar = {
            ComposedWeatherAppBarUI(
                title = "Home Screen",
                onBackPressed = onBackPressed,
                backButtonIcon = Icons.Default.ArrowBack
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            AnimatedVisibility(
                visible = isShowing,
                enter = fadeIn() + slideInVertically { 5000 },
                exit = fadeOut() + slideOutVertically { -5000 },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color)
                    .navigationBarsPadding()
                    .animateContentSize()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    textAlign = TextAlign.Center,
                    text = if (state.isConnected) "Connected" else "Offline"
                )
            }
        }
    ) { paddingValues ->

        LaunchedEffect(state.error) {
            if (state.error != null) {
                val result = snackBarHostState.showSnackbar(
                    message = state.error ?: "Something went wrong",
                    withDismissAction = true,
                    duration = SnackbarDuration.Indefinite
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        state.error = null
                    }

                    SnackbarResult.Dismissed -> {
                        state.error = null
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    end = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                )
                .background(Color.Green)
        ) {
            if (state.isLoading) {
                ContentLoaderUI()
            } else {
                HomeUiContent(
                    state = state,
                    modifyContent = {
                        viewModel.modifyContent(state = it)
                    },
                    navigateAhead = {
                        navigateAhead()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUiContent(
    state: HomeViewState,
    modifyContent: (HomeViewState) -> Unit,
    navigateAhead: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Content Loaded",
            modifier = Modifier
                .padding(24.dp)
                .clickable {
                    navigateAhead()
                }
        )
        FilterChip(
            shape = RoundedCornerShape(50),
            selected = true,
            onClick = {
                if (state.weatherDataRequest.temperatureUnit == Constants.CELSIUS)
                    modifyContent(
                        state.copy(
                            weatherDataRequest = state.weatherDataRequest.copy(
                                temperatureUnit = Constants.FAHRENHEIT
                            )
                        )
                    )
                else
                    modifyContent(
                        state.copy(
                            weatherDataRequest = state.weatherDataRequest.copy(
                                temperatureUnit = Constants.CELSIUS
                            )
                        )
                    )
            },
            label = {
                Text(
                    text = state.weatherDataRequest.temperatureUnit,
                    modifier = Modifier.padding(4.dp)
                )
            }
        )
    }
}

@Preview
@Composable
fun HomeUIPreview() {
    ComposedWeatherTheme {
        HomeUI(
            onBackPressed = {},
            navigateAhead = {}
        )
    }
}