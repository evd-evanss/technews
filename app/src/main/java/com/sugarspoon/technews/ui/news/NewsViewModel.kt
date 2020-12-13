package com.sugarspoon.technews.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sugarspoon.technews.data.remote.datasource.NewsRepository
import com.sugarspoon.technews.data.remote.model.Article
import com.sugarspoon.technews.utils.extensions.onCollect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsViewModel (
    private val newsRepository: NewsRepository
) : ViewModel() {

    val state = StateNewsActivity(
        articles = MutableStateFlow(mutableListOf()),
        loading = MutableStateFlow(false),
        error = MutableStateFlow("")
    )

    fun getNews() = viewModelScope.launch {
        state.run {
            newsRepository.getNews().onCollect(
                onLoading = {
                    loading.value = it
                },
                onSuccess = {
                    articles.value = it.articles
                },
                onError = {
                    error.value = it.message ?: UNKNOWN_ERROR
                }
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory constructor(
        private val newsRepository: NewsRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewsViewModel(
                newsRepository = newsRepository
            ) as T
        }
    }

    companion object {
        private const val UNKNOWN_ERROR = "Erro desconhecido"
    }
}

data class StateNewsActivity(
    val articles: MutableStateFlow<List<Article>>,
    val loading: MutableStateFlow<Boolean>,
    val error: MutableStateFlow<String>
)
