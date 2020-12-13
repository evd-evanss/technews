package com.sugarspoon.technews.data.remote.datasource

import com.sugarspoon.technews.BuildConfig
import com.sugarspoon.technews.data.remote.model.NewsResponse
import com.sugarspoon.technews.data.remote.repository.NewsRepositoryInterface
import com.sugarspoon.technews.data.remote.repository.RetrofitServiceFactory
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class NewsDataSource @Inject constructor(
    retrofit: Retrofit.Builder
) : NewsRepositoryInterface {

    private val service: Service = RetrofitServiceFactory(retrofit).newInstance()

    override suspend fun getNews(): Flow<NewsResponse> = service.getNews()

    interface Service {

        @GET("top-headlines?country=br")
        fun getNews(
            @Query("category") category: String = "technology",
            @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
        ): Flow<NewsResponse>
    }
}

