package com.manish.newsapp.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manish.newsapp.data.model.APIResponse
import com.manish.newsapp.data.model.Article
import com.manish.newsapp.data.util.Resource
import com.manish.newsapp.domain.usecase.GetNewsHeadlineUseCase
import com.manish.newsapp.domain.usecase.GetSearchedNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val applicationContext: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
) : AndroidViewModel(applicationContext) {
    val newsHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetworkAvailable(applicationContext)) {
                newsHeadlines.postValue(Resource.Loading())
                val apiResult = getNewsHeadlineUseCase.execute(country, page)
                newsHeadlines.postValue(apiResult)
            } else {
                newsHeadlines.postValue(Resource.Error("Internet not available"))
            }
        } catch (exception: Exception) {
            newsHeadlines.postValue(Resource.Error(exception.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    //search functionality

    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun searchNews(country: String, page: Int, searchQuery: String) =
        viewModelScope.launch {
            try {
                searchedNews.postValue(Resource.Loading())
                if (isNetworkAvailable(applicationContext)) {
                    val searchResult = getSearchedNewsUseCase.execute(country, page, searchQuery)
                    searchedNews.postValue(searchResult)
                } else {
                    searchedNews.postValue(Resource.Error("Internet not available"))
                }
            } catch (exception: Exception) {
                newsHeadlines.postValue(Resource.Error(exception.message.toString()))
            }
        }
}