package com.manish.newsapp.domain.usecase

import com.manish.newsapp.data.model.APIResponse
import com.manish.newsapp.data.util.Resource
import com.manish.newsapp.domain.repository.NewsRepository

class GetNewsHeadlineUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(countryCode: String, page: Int): Resource<APIResponse> =
        newsRepository.getNewsHeadlines(countryCode, page)
}