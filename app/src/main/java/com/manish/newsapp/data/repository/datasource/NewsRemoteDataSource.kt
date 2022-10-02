package com.manish.newsapp.data.repository.datasource

import com.manish.newsapp.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(countryCode: String, page: Int): Response<APIResponse>
    suspend fun getSearchedTopHeadlines(
        countryCode: String,
        page: Int,
        searchQuery: String
    ): Response<APIResponse>
}