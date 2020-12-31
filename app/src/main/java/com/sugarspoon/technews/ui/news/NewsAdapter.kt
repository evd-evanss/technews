package com.sugarspoon.technews.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sugarspoon.domain.Article
import com.sugarspoon.technews.R
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.android.synthetic.main.item_article.view.*
import javax.inject.Inject

@ActivityScoped
class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var articles = mutableListOf<Article>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClicked: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)

        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) {
            itemView.run {
                textArticleTitle.text = article.title
                textArticleDescription.text = article.description
                textArticleSource.text = article.source.name

                Glide
                    .with(itemView)
                    .load(article.urlToImage)
                    .centerCrop()
                    .into(imageArticle)
                setOnClickListener {
                    onItemClicked?.invoke(article.url)
                }
            }
        }
    }
}