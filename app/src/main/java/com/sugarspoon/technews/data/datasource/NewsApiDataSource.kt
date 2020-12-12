package com.sugarspoon.technews.data.datasource

import com.sugarspoon.technews.data.remote.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsApiDataSource {

    fun fetchNews(): Flow<NewsResponse>
}