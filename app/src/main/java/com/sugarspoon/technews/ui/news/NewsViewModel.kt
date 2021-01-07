package com.sugarspoon.technews.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sugarspoon.technews.base.BaseViewModel
import com.sugarspoon.technews.data.remote.repository.NewsRepository
import com.sugarspoon.technews.data.remote.model.Article
import com.sugarspoon.technews.ui.news.routes.StateNewsRoutes
import com.sugarspoon.technews.ui.news.states.StateNewsView
import com.sugarspoon.technews.utils.extensions.onCollect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsViewModel (
    private val newsRepository: NewsRepository
) : BaseViewModel<StateNewsView, StateNewsRoutes>() {

    override val initialViewState: StateNewsView
        get() = StateNewsView()

    override fun navigateByRoute(routes: StateNewsRoutes) {
        routes.openWebView()
    }

    fun getNews() = viewModelScope.launch {
        state.run {
            newsRepository.getNews().onCollect(
                onLoading = {
                    state.value = StateNewsView(loading = it)
                },
                onSuccess = {
                    state.value = StateNewsView(
                        articles = it.articles,
                        loading = false
                    )
                },
                onError = {
                    state.value = StateNewsView(error = it.message ?: UNKNOWN_ERROR)
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


