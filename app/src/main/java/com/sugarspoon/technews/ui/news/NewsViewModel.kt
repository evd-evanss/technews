package com.sugarspoon.technews.ui.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sugarspoon.technews.data.remote.model.Article
import com.sugarspoon.technews.data.remote.repository.NewsRepositoryInterface
import com.sugarspoon.technews.utils.extensions.onCollect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepositoryInterface
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

    companion object {
        private const val UNKNOWN_ERROR = "Erro desconhecido"
    }
}

data class StateNewsActivity(
    val articles: MutableStateFlow<List<Article>>,
    val loading: MutableStateFlow<Boolean>,
    val error: MutableStateFlow<String>
)
