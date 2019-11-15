package com.example.swivel.ui.fragments.headline

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swivel.BuildConfig
import com.example.swivel.dao.Article
import com.example.swivel.dao.SuccessResult
import com.example.swivel.net.RestFulWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadLinesViewModel : ViewModel() {

    private val mArticles: MutableList<Article> = ArrayList()

    private var mCountry: String = "us"

    private val liveData = MutableLiveData<List<Article>>().apply {
        value = mArticles
    }

    val articles: LiveData<List<Article>> = liveData

    init {
        fetchNews()
    }

    private fun fetchNews() {
        RestFulWebService.getWebService().getTopHeadlines(country = mCountry).enqueue(object : Callback<SuccessResult> {

            override fun onFailure(call: Call<SuccessResult>, t: Throwable) {
                if (BuildConfig.DEBUG) {
                    Log.e("headlines-vm", t.message, t)
                } else {
                    //todo: Report with fabric crashlytics
                }
            }

            override fun onResponse(call: Call<SuccessResult>, response: Response<SuccessResult>) {
                if (response.isSuccessful) {
                    mArticles.clear()
                    response.body()?.articles?.let { list ->
                        mArticles.addAll(0, list.filter { !it.title.isNullOrBlank() })
                    }
                    liveData.postValue(mArticles)
                }
            }
        })
    }

    fun setCountry(country: String) {
        if (mCountry != country) {
            mCountry = country
            fetchNews()
        }
    }
}