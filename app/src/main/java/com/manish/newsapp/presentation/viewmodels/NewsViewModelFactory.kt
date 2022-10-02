package com.manish.newsapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manish.newsapp.domain.usecase.GetNewsHeadlineUseCase
import com.manish.newsapp.domain.usecase.GetSearchedNewsUseCase

class NewsViewModelFactory(
    private val applicationContext: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            applicationContext,
            getNewsHeadlineUseCase,
            getSearchedNewsUseCase
        ) as T
    }
}