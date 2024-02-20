package com.example.data.repositories.contract

import com.example.core_network.NetworkResult
import com.example.data.model.response.RepositoryListResponse

interface ProjectsRepository {

    suspend fun getProjects(query: String): NetworkResult<RepositoryListResponse>

}