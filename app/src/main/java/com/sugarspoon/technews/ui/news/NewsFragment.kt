package com.sugarspoon.technews.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sugarspoon.technews.R
import com.sugarspoon.technews.utils.extensions.collectFrom
import com.sugarspoon.technews.utils.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCollectors()
        viewModel.getNews()
    }

    private fun setupCollectors() {
        collectArticles()
        collectLoading()
    }

    private fun collectArticles() = view?.run {
        with(viewModel) {
            collectFrom(
                flow = state.articles,
                result = {
                    with(recyclerArticles) {
                        setHasFixedSize(true)
                        adapter = NewsAdapter(it)
                    }
                }
            )
        }
    }

    private fun collectLoading() = view?.run {
        with(viewModel) {
            collectFrom(
                flow = state.loading,
                result = { loadingArticles.setVisible(it) }
            )
        }
    }
}
