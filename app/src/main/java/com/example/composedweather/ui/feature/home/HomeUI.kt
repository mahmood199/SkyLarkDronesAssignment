package com.example.composedweather.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedweather.ui.common.ComposedWeatherAppBarUI
import com.example.composedweather.ui.common.ContentLoaderUI
import com.example.composedweather.ui.theme.ComposedWeatherTheme

@Composable
fun HomeUI(
    onBackPressed: () -> Unit,
    navigateAhead: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            ComposedWeatherAppBarUI(
                title = "Home Screen",
                onBackPressed = onBackPressed,
                backButtonIcon = Icons.Default.ArrowBack
            )
        }
    ) { paddingValues ->
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray),
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
                }
            }
        }
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