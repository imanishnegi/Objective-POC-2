package com.manish.newsapp.domain.usecase

import com.manish.newsapp.data.model.APIResponse
import com.manish.newsapp.data.util.Resource
import com.manish.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(
        countryCode: String,
        page: Int,
        searchQuery: String
    ): Resource<APIResponse> =
        newsRepository.getSearchedNews(countryCode, page, searchQuery)
}