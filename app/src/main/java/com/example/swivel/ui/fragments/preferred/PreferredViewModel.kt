package com.example.swivel.ui.fragments.preferred

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swivel.dao.Article
import com.example.swivel.dao.SuccessResult
import com.example.swivel.net.RestFulWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreferredViewModel : ViewModel() {

    private var mKeyword: String = "bitcoin"

    private val mArticles: MutableList<Article> = ArrayList()

    private val liveData = MutableLiveData<List<Article>>().apply {
        value = mArticles
    }

    val articles: LiveData<List<Article>> = liveData

    init {
        fetchNews()
    }

    private fun fetchNews() {
        RestFulWebService.getWebService().getEverything(mKeyword).enqueue(object : Callback<SuccessResult> {

            override fun onFailure(call: Call<SuccessResult>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    fun setKeyword(keyword: String) {
        if (mKeyword != keyword) {
            mKeyword = keyword
            fetchNews()
        }
    }
}