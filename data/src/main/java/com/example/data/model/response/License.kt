package com.example.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class License(
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("spdx_id") val spdxId: String,
    @SerializedName("url") val url: String
): Parcelable