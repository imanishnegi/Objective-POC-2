package com.manish.newsapp.domain.usecase

import com.manish.newsapp.domain.repository.NewsRepository

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute() = newsRepository.getSavedNewsArticles()
}