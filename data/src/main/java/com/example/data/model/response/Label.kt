package com.example.data.model.response

import com.google.gson.annotations.SerializedName

data class Label(
    @SerializedName("color") val color: String,
    @SerializedName("default") val defaultLabel: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("url") val url: String
)