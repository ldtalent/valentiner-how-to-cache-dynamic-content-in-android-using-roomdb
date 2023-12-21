package com.valentinerutto.datacacheroom.data

import com.valentinerutto.datacacheroom.data.local.dao.NewsDao
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.mappers.mapResponseToEntity
import com.valentinerutto.datacacheroom.data.remote.Resource
import com.valentinerutto.datacacheroom.data.remote.api.ApiService
import com.valentinerutto.datacacheroom.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class NewsRepository(private val newsDao: NewsDao, private val apiService: ApiService) {
suspend fun getBreakingNews(): Flow<Resource<List<NewsEntity>>> {
val response = apiService.getBreakingNews(Constants.API_KEY)
    if (response.isSuccessful && response.body()?.articles!!.isNotEmpty()){
        val entity = mapResponseToEntity(response.body()!!)
    }
 return emptyFlow()
}
}