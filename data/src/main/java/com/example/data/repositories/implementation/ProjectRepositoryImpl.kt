package com.example.data.repositories.implementation

import com.example.core_network.NetworkResult
import com.example.data.model.response.Issue
import com.example.data.model.response.Item
import com.example.data.model.response.RepositoryListResponse
import com.example.data.remote.IssuesRemoteDataSource
import com.example.data.remote.RepositorySearchRemoteDataSource
import com.example.data.repositories.contract.ProjectsRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val repositorySearchDataSource: RepositorySearchRemoteDataSource,
    private val repositoryIssuesDataSource: IssuesRemoteDataSource
): ProjectsRepository {

    override suspend fun getProjects(query: String): NetworkResult<RepositoryListResponse> {
        return repositorySearchDataSource.getRepositories(query)
    }

    override suspend fun getIssues(issuesUrl: String): NetworkResult<List<Issue>> {
        return repositoryIssuesDataSource.getAllIssues(issuesUrl)
    }
}