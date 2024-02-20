package com.example.domain.repositories

import com.example.core_network.NetworkResult
import com.example.data.model.response.RepositoryListResponse
import com.example.data.repositories.contract.ProjectsRepository
import javax.inject.Inject

class SortRepositoriesByStarsUseCase @Inject constructor(
    private val repository: ProjectsRepository
) {

    suspend fun getProjects(query: String): NetworkResult<RepositoryListResponse> {
        val result = repository.getProjects(query)
        when (result) {
            is NetworkResult.Success -> {
                val inputList = result.data.items.sortedByDescending {
                    it.stargazersCount
                }
                val outputResult = result.data.copy(items = inputList)
                return NetworkResult.Success(outputResult)
            }
            is NetworkResult.Exception -> return result
            is NetworkResult.RedirectError -> return result
            is NetworkResult.ServerError -> return result
            is NetworkResult.UnAuthorised -> return result
        }
    }

}