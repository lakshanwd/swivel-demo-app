package com.example.swivel.dao


import com.google.gson.annotations.SerializedName

data class SuccessResult(
    @SerializedName("articles")
    val articles: List<Article>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)