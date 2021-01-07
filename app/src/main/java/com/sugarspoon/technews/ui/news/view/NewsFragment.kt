package com.sugarspoon.technews.ui.news.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sugarspoon.technews.R
import com.sugarspoon.technews.data.remote.repository.NewsRepository
import com.sugarspoon.technews.ui.news.NewsViewModel
import com.sugarspoon.technews.ui.news.routes.StateNewsRoutes
import com.sugarspoon.technews.ui.web.WebViewActivity
import com.sugarspoon.technews.ui.web.webViewIntent
import com.sugarspoon.technews.utils.extensions.collectFrom
import com.sugarspoon.technews.utils.extensions.safeCollect
import com.sugarspoon.technews.utils.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

class NewsFragment : Fragment(R.layout.main_fragment) {

    private val factory = NewsViewModel.Factory(NewsRepository())
    private val viewModel by viewModels<NewsViewModel> { factory }
    private val adapter by lazy {
        NewsAdapter(mutableListOf())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews()
        setupCollectors()
    }

    private fun setupCollectors() = view?.run {
        viewModel.state.safeCollect { state ->
            state?.let {
                loadingArticles?.setVisible(it.loading ?: false)
                recyclerArticles?.run {
                    with(this) {
                        setHasFixedSize(true)
                        adapter = NewsAdapter(it.articles ?: mutableListOf())
                    }
                }
            }
        }
    }
}
