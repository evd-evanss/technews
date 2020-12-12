package com.sugarspoon.technews.data.repository

import com.sugarspoon.technews.data.remote.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryInterface {

    suspend fun getNews(): Flow<NewsResponse>
}