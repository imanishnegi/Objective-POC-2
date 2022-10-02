package com.manish.newsapp.domain.usecase

import com.manish.newsapp.data.model.Article
import com.manish.newsapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}