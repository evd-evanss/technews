package com.sugarspoon.data

import com.sugarspoon.domain.NewsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsDataRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

    suspend fun getNews() = newsDataSource.getNews()
}

interface NewsDataSource {

    suspend fun getNews(): Flow<NewsResponse>
}