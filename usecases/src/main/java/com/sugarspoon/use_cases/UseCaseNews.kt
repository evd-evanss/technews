package com.sugarspoon.use_cases

import com.sugarspoon.data.NewsDataRepository
import javax.inject.Inject

class UseCaseNews @Inject constructor (
    private val repository: NewsDataRepository
){

    suspend fun getNews() = repository.getNews()
}