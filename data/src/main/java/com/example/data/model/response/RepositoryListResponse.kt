package com.example.data.model.response

import com.google.gson.annotations.SerializedName

data class RepositoryListResponse(
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<Item>,
    @SerializedName("total_count") val totalCount: Int
)