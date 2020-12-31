package com.sugarspoon.domain

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val url: String,
    val source: Source,
    val title: String,
    val urlToImage: String
)