package com.example.composedweather.ui.feature.issues

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RepositoryIssuesUIContainer(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RepositoriesIssuesViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()



}