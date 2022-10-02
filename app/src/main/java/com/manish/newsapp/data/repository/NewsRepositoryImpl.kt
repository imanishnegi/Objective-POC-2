package com.manish.newsapp.data.repository

import com.manish.newsapp.data.model.APIResponse
import com.manish.newsapp.data.model.Article
import com.manish.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.manish.newsapp.data.util.Resource
import com.manish.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
) : NewsRepository {
    override suspend fun getNewsHeadlines(countryCode: String, page: Int): Resource<APIResponse> {
        return responseToResult(newsRemoteDataSource.getTopHeadlines(countryCode, page))
    }

    override suspend fun getSearchedNews(
        countryCode: String,
        page: Int,
        searchQuery: String
    ): Resource<APIResponse> {
        return responseToResult(
            newsRemoteDataSource.getSearchedTopHeadlines(
                countryCode,
                page,
                searchQuery
            )
        )
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNewsArticles(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    private fun responseToResult(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}