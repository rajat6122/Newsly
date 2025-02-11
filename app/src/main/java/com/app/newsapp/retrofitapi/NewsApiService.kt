package com.app.newsapp.retrofitapi

import com.app.newsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    // Search by user
    @GET("everything")
    fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = "4eb4fe91f29040ea823bf2be3e2e474e"
    ): Call<NewsResponse>
}