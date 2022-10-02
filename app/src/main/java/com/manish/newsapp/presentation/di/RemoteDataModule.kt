package com.manish.newsapp.presentation.di

import com.manish.newsapp.data.api.NewsApiService
import com.manish.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.manish.newsapp.data.repository.datasourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService) : NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApiService)
    }
}