package com.manish.newsapp.domain.repository

import com.manish.newsapp.data.model.APIResponse
import com.manish.newsapp.data.model.Article
import com.manish.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(countryCode: String, page: Int): Resource<APIResponse>
    suspend fun getSearchedNews(
        countryCode: String,
        page: Int,
        searchQuery: String
    ): Resource<APIResponse>

    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNewsArticles(): Flow<List<Article>>
}