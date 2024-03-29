package com.valentinerutto.datacacheroom.data.remote

import com.valentinerutto.datacacheroom.data.local.dao.NewsDao
import com.valentinerutto.datacacheroom.data.remote.api.ApiService
import com.valentinerutto.datacacheroom.utils.Constants

class NewsRemoteDataSource(
    private val newsDao: NewsDao,
    private val apiService: ApiService
) {
    suspend fun getBreakingNews() = apiService.getBreakingNews(Constants.API_KEY)

//    val newsFlow: Flow<List<NewsEntity>>
//        get() = newsDao.getNewsList()
}