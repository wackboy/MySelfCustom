package com.example.myselfcustom.arch_retrofit_coroutine

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("id") var id: Long = 0,
    @SerializedName("title") var title: String = "",
    @SerializedName("desc") var desc: String = "",
    @SerializedName("url") var url: String = ""
)