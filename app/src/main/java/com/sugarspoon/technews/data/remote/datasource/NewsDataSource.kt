package com.sugarspoon.technews.data.remote.datasource

import com.sugarspoon.technews.data.remote.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {

    suspend fun getNews(): Flow<NewsResponse>
}