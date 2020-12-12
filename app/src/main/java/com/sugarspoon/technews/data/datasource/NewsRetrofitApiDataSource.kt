package com.sugarspoon.technews.data.datasource

import com.sugarspoon.technews.data.service.ApiService
import javax.inject.Inject

class NewsRetrofitApiDataSource @Inject constructor(
    private val newsApiClient: ApiService
) : NewsApiDataSource {

    override fun fetchNews() = newsApiClient.getNews()
}