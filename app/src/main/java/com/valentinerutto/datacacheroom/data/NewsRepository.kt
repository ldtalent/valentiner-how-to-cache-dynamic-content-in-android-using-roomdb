package com.valentinerutto.datacacheroom.data

import com.valentinerutto.datacacheroom.data.local.dao.NewsDao
import com.valentinerutto.datacacheroom.data.remote.api.ApiService

class NewsRepository(private val newsDao: NewsDao, private val apiService: ApiService) {
}