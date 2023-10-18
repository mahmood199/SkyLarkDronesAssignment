package com.example.composedweather

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedweather.feature.detail.DetailUI
import com.example.composedweather.feature.home.HomeUI
import com.example.composedweather.feature.splash.SplashUI
import com.example.composedweather.ui.theme.ComposedWeatherTheme

@Composable
fun CentralNavigation(
    backPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.name,
        modifier = modifier
    ) {
        composable(route = Screen.Splash.name) {
            SplashUI(
                navigateToHome = {
                    navController.navigate(route = Screen.Home.name) {
                        popUpTo(Screen.Splash.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = Screen.Home.name) {
            HomeUI()
        }

        composable(route = Screen.Detail.name) {
            DetailUI()
        }


    }

}

@Preview
@Composable
fun CentralNavigationPreview() {
    ComposedWeatherTheme {
        CentralNavigation(
            backPress = {}
        )
    }
}