package com.example.composedweather.ui


object ScreenName {
    const val HOME = "home"
    const val DETAIL = "detail"
    const val SPLASH = "splash"
    const val REPOSITORY_SEARCH = "repository_search"


    const val REPOSITORY_ISSUES = "repository_issues"
}

object Arguments {
    const val issueId = "issues_id"
}

sealed class Screen(val name: String) {
    data object Home : Screen(name = ScreenName.HOME)
    data object Detail : Screen(name = ScreenName.DETAIL)
    data object Splash : Screen(name = ScreenName.SPLASH)
    data object RepositorySearch : Screen(name = ScreenName.REPOSITORY_SEARCH)
    data object RepositoryIssues : Screen(name = ScreenName.REPOSITORY_ISSUES) {

        fun getPath(): String {
            return ScreenName.REPOSITORY_ISSUES.plus("/{${Arguments.issueId}}")
        }
        fun getPathWthId(id: String): String {
            return ScreenName.REPOSITORY_ISSUES.plus("/${id}")
        }
    }
}