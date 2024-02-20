package com.example.data.repositories.implementation

import com.example.core_network.NetworkResult
import com.example.data.model.response.RepositoryListResponse
import com.example.data.remote.RepositorySearchRemoteDataSource
import com.example.data.repositories.contract.ProjectsRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val remoteDataSource: RepositorySearchRemoteDataSource
): ProjectsRepository {

    override suspend fun getProjects(query: String): NetworkResult<RepositoryListResponse> {
        return remoteDataSource.getRepositories(query)
    }
}