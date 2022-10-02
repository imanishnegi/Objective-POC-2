package com.manish.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.manish.newsapp.data.model.Article
import com.manish.newsapp.databinding.NewsListItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val asyncDiffer = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = asyncDiffer.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return asyncDiffer.currentList.size
    }

    inner class NewsViewHolder(val newsListItemBinding: NewsListItemBinding) :
        RecyclerView.ViewHolder(newsListItemBinding.root) {
        fun bind(article: Article) {
            newsListItemBinding.tvTitle.text = article.title
            newsListItemBinding.tvDescription.text = article.description
            newsListItemBinding.tvSource.text = article.source.name
            newsListItemBinding.tvPublishedAt.text = article.publishedAt

            Glide.with(newsListItemBinding.ivArticleImage.context)
                .load(article.urlToImage)
                .into(newsListItemBinding.ivArticleImage)
            newsListItemBinding.root.setOnClickListener {
                onItemClickListener?.let { onClicked ->
                    onClicked(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: ((Article) -> Unit)) {
        onItemClickListener = listener
    }
}