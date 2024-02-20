package com.example.composedweather.ui.feature.issues

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.data.model.response.Issue

@Composable
fun RepositoryIssuesUIContainer(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RepositoriesIssuesViewModel = hiltViewModel()
) {

    BackHandler {
        onBackPressed()
    }

    val state by viewModel.state.collectAsState()

    val issues = viewModel.issues

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        items(
            count = issues.size,
            key = {
                val item = issues[it]
                item.id
            }
        ) {
            val item = issues[it]
            IssueUI(item)
        }
    }
}

@Composable
fun IssueUI(
    item: Issue,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = item.title)
        Text(text = item.state)
        Text(text = "Assignee Profile - ${item.assignee.url}")
    }
}
