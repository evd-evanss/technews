package com.sugarspoon.technews.data.remote.datasource

import com.sugarspoon.technews.BuildConfig
import com.sugarspoon.technews.data.remote.model.NewsResponse
import com.sugarspoon.technews.data.remote.repository.NewsDataSource
import com.sugarspoon.technews.data.remote.repository.RetrofitServiceFactory
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class NewsRepository : NewsDataSource {

    private val service: Service = RetrofitServiceFactory(Retrofit.Builder()).newInstance()

    override suspend fun getNews(): Flow<NewsResponse> = service.getNews()

    interface Service {

        @GET("top-headlines?country=br")
        fun getNews(
            @Query("category") category: String = "technology",
            @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
        ): Flow<NewsResponse>
    }
}

