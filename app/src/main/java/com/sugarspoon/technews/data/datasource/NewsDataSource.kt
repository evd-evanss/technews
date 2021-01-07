package com.sugarspoon.technews.data.datasource

import com.sugarspoon.technews.data.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {

    suspend fun getNews(): Flow<NewsResponse>
}