package com.sugarspoon.technews.data.service

import com.sugarspoon.technews.BuildConfig
import com.sugarspoon.technews.data.remote.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?country=br")
    fun getNews(
        @Query("category") category: String = "technology",
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): Flow<NewsResponse>
}