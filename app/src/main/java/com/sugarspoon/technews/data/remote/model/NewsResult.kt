package com.sugarspoon.technews.data.remote.model

sealed class NewsResult {
    class Success(val articles: List<Article>) : NewsResult()
    class ApiError(val code: Int, val message: String?) : NewsResult()
    class UnknownError(val message: String?) : NewsResult()
}