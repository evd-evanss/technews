package com.sugarspoon.use_cases

import com.sugarspoon.data.NewsDataSourceRepository
import javax.inject.Inject

class UseCaseNews @Inject constructor (
    private val repository: NewsDataSourceRepository
){

    suspend fun getNews() = repository.getNews()
}