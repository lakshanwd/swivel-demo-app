package com.example.swivel.net

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestFulWebService {

    companion object {
        private val mWebService: WebService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
                            .setExclusionStrategies(object : ExclusionStrategy {
                                override fun shouldSkipField(f: FieldAttributes): Boolean {
                                    return false
                                }

                                override fun shouldSkipClass(clazz: Class<*>): Boolean {
                                    return false
                                }
                            })
                            .create()
                    )
                )
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addInterceptor {
                            val request = it.request().newBuilder()
                                .addHeader("X-Api-Key", "c6a3e159741a4309bcf8931bf311a56e")
                                .build()
                            it.proceed(request)
                        }
                        .build()
                )
                .build()
            retrofit.create(WebService::class.java)
        }

        fun getWebService() = mWebService
    }
}