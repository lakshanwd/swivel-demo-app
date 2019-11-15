package com.example.swivel.net

import com.example.swivel.dao.SuccessResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("v2/top-headlines")
    fun getTopHeadlines(@Query("country") country: String? = "us"): Call<SuccessResult>

    @GET("v2/top-headlines")
    fun getEverything(@Query("q") q: String? = null): Call<SuccessResult>

}
