package com.sugarspoon.technews.ui.news.states

import com.sugarspoon.technews.data.model.Article

data class StateNewsView(
    val articles: List<Article>? = null,
    val loading: Boolean? = null,
    val error: String? = null
)