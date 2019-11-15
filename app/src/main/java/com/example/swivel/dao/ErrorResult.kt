package com.example.swivel.dao


import com.google.gson.annotations.SerializedName

data class ErrorResult(
    @SerializedName("code")
    var code: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)