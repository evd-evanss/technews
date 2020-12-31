package com.sugarspoon.technews.framework.repository

import com.sugarspoon.technews.utils.NetworkConstants
import com.sugarspoon.data.NewsDataSource
import com.sugarspoon.domain.NewsResponse
import com.sugarspoon.technews.utils.RetrofitServiceFactory
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class NewsDataRepoImp @Inject constructor(
    retrofit: Retrofit.Builder
): NewsDataSource {

    private val service: Service = RetrofitServiceFactory(retrofit).newInstance()

    override suspend fun getNews() = service.getNews()
}

interface Service {

    @GET(NetworkConstants.HEADLINES)
    fun getNews(
        @Query(NetworkConstants.CATEGORY) category: String = NetworkConstants.TECHNOLOGY
    ): Flow<NewsResponse>
}