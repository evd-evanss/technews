package com.sugarspoon.technews.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sugarspoon.technews.R
import com.sugarspoon.technews.ui.landingpage.WebViewActivity.Companion.getIntent
import com.sugarspoon.technews.utils.extensions.collectFrom
import com.sugarspoon.technews.utils.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: NewsViewModel by viewModels()
    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollectors()
        setupListeners()
    }

    private fun setupCollectors() {
        collectArticles()
        collectLoading()
    }

    private fun setupListeners() {
        view?.run {
            newsAdapter.onItemClicked = { url ->
                requireContext().run {
                    startActivity(this.getIntent(url = url))
                }
            }
        }
    }

    private fun collectArticles() = view?.run {
        with(viewModel) {
            collectFrom(
                flow = state.articles ?: return@run,
                result = {
                    recyclerArticles?.run {
                        with(this) {
                            setHasFixedSize(true)
                            newsAdapter.articles = it.toMutableList()
                            adapter = newsAdapter
                        }
                    }
                }
            )
        }
    }

    private fun collectLoading() = view?.run {
        with(viewModel) {
            collectFrom(
                flow = state.loading ?: return@run,
                result = { loadingArticles.setVisible(it) }
            )
        }
    }
}
