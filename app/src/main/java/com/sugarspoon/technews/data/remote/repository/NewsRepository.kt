package com.sugarspoon.technews.data.remote.repository

import com.sugarspoon.technews.BuildConfig
import com.sugarspoon.technews.data.NetworkConstants.API_KEY
import com.sugarspoon.technews.data.NetworkConstants.CATEGORY
import com.sugarspoon.technews.data.NetworkConstants.HEADLINES
import com.sugarspoon.technews.data.NetworkConstants.TECHNOLOGY
import com.sugarspoon.technews.data.remote.datasource.NewsDataSource
import com.sugarspoon.technews.data.remote.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class NewsRepository @Inject constructor(
    retrofit: Retrofit.Builder
) : NewsDataSource {

    private val service: Service = RetrofitServiceFactory(retrofit).newInstance()

    override suspend fun getNews(): Flow<NewsResponse> = service.getNews()

    interface Service {

        @GET(HEADLINES)
        fun getNews(
            @Query(CATEGORY) category: String = TECHNOLOGY,
            @Query(API_KEY) apiKey: String = BuildConfig.NEWS_API_KEY
        ): Flow<NewsResponse>
    }
}

