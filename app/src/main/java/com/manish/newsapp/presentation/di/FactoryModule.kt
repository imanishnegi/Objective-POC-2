package com.manish.newsapp.presentation.di

import android.app.Application
import com.manish.newsapp.domain.usecase.GetNewsHeadlineUseCase
import com.manish.newsapp.domain.usecase.GetSearchedNewsUseCase
import com.manish.newsapp.presentation.viewmodels.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        newsHeadlineUseCase: GetNewsHeadlineUseCase,
        searchedNewsUseCase: GetSearchedNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            application,
            newsHeadlineUseCase, searchedNewsUseCase
        )
    }
}