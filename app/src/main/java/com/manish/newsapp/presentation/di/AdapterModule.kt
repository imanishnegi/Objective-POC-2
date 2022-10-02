package com.manish.newsapp.presentation.di

import android.app.Application
import com.manish.newsapp.domain.usecase.GetNewsHeadlineUseCase
import com.manish.newsapp.presentation.adapter.NewsAdapter
import com.manish.newsapp.presentation.viewmodels.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideNewsAdapter() : NewsAdapter {
        return NewsAdapter()
    }
}