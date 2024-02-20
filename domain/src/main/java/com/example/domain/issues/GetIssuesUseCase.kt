package com.example.domain.issues

import com.example.core_network.NetworkResult
import com.example.data.model.response.Issue
import com.example.data.repositories.contract.ProjectsRepository
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val repository: ProjectsRepository
) {

    suspend fun getAllIssues(issuesUrl: String): NetworkResult<List<Issue>> {
        return repository.getIssues(issuesUrl)
    }
}