package com.example.composedweather.ui

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composedweather.ui.feature.home.HomeUI
import com.example.composedweather.ui.feature.issues.RepositoryIssuesUIContainer
import com.example.composedweather.ui.feature.repository_search.RepositorySearchUIContainer
import com.example.composedweather.ui.feature.search.DetailUI
import com.example.composedweather.ui.feature.splash.SplashUI
import com.example.composedweather.ui.theme.ComposedWeatherTheme
import com.example.data.model.response.Item
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CentralNavigation(
    backPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.RepositorySearch.name,
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

        composable(
            route = Screen.Home.name,
            enterTransition = {
                fadeIn()
            }
        ) {
            HomeUI(
                onBackPressed = {
                    backPress()
                },
                navigateToSearch = {
                    navController.navigate(Screen.Detail.name)
                }
            )
        }

        composable(
            route = Screen.Detail.name,
            enterTransition = {
                fadeIn()
            }
        ) {
            DetailUI(onBackPressed = {
                navController.popBackStack()
            })
        }

        composable(route = Screen.RepositorySearch.name) {
            RepositorySearchUIContainer(
                onBackPressed = {
                    navController.popBackStack()
                },
                navigateToViewIssues = {
                    val suffixRemoved = it.issuesUrl.replace("{/number}", "")
                    val encodedUrl = URLEncoder.encode(suffixRemoved, StandardCharsets.UTF_8.toString())
                    navController.navigate(
                        route = Screen.RepositoryIssues.getPathWthId(id = encodedUrl).plus("/"),
                    )
                }
            )
        }

        composable(
            route = Screen.RepositoryIssues.getPath2(),
            arguments = listOf(
                navArgument(name = Arguments.issueId) {
                    type = NavType.StringType
                },
                navArgument(name = Arguments.repositoryItem) {
                    type = NavType.ParcelableType(Item::class.java)
                }
            )
        ) {
            RepositoryIssuesUIContainer(
                onBackPressed = {
                    navController.popBackStack()
                },
            )
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