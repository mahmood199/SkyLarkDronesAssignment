package com.example.composedweather.ui


object ScreenName {
    const val HOME = "home"
    const val DETAIL = "detail"
    const val SPLASH = "splash"
    const val REPOSITORY_SEARCH = "repository_search"


    const val REPOSITORY_ISSUES = "repository_issues"
    const val issue_id = "issues_id"
}


sealed class Screen(val name: String) {
    data object Home : Screen(name = ScreenName.HOME)
    data object Detail : Screen(name = ScreenName.DETAIL)
    data object Splash : Screen(name = ScreenName.SPLASH)
    data object RepositorySearch : Screen(name = ScreenName.REPOSITORY_SEARCH)
    data object RepositoryIssues : Screen(name = ScreenName.REPOSITORY_ISSUES.plus("/${ScreenName.issue_id}"))
}