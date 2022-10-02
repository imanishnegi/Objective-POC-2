package com.manish.newsapp.data.repository.datasourceImpl

import com.manish.newsapp.data.api.NewsApiService
import com.manish.newsapp.data.model.APIResponse
import com.manish.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService,
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(countryCode: String, page: Int): Response<APIResponse> {
        return newsApiService.getTopHeadlines(countryCode, page)
    }

    override suspend fun getSearchedTopHeadlines(
        countryCode: String,
        page: Int,
        searchQuery: String
    ): Response<APIResponse> {
        return newsApiService.getSearchedTopHeadlines(countryCode,page,searchQuery)
    }
}