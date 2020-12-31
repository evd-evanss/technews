package com.sugarspoon.data

import com.sugarspoon.data.NetworkConstants.CATEGORY
import com.sugarspoon.data.NetworkConstants.HEADLINES
import com.sugarspoon.data.NetworkConstants.TECHNOLOGY
import com.sugarspoon.domain.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class NewsDataSourceRepository @Inject constructor(
    retrofit: Retrofit.Builder
) : NewsDataSource {

    private val service: Service = RetrofitServiceFactory(retrofit).newInstance()

    override suspend fun getNews() = service.getNews()

    interface Service {

        @GET(HEADLINES)
        fun getNews(
            @Query(CATEGORY) category: String = TECHNOLOGY
        ): Flow<NewsResponse>
    }
}

interface NewsDataSource {

    suspend fun getNews(): Flow<NewsResponse>
}