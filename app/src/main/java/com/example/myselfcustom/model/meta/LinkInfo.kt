package com.example.myselfcustom.model.meta

import com.google.gson.annotations.SerializedName

data class LinkInfo(
    @SerializedName("category") val category: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("visible") val visible: Int
)