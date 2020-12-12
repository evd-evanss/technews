package com.sugarspoon.technews.data.repository

import android.content.Context
import com.sugarspoon.technews.data.datasource.NewsApiDataSource
import com.sugarspoon.technews.data.remote.model.NewsResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val newsApiDataSource: NewsApiDataSource
) : NewsRepositoryInterface {

    override suspend fun getNews(): Flow<NewsResponse> = newsApiDataSource.fetchNews()
}