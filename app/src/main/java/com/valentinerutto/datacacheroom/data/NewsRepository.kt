package com.valentinerutto.datacacheroom.data

import com.valentinerutto.datacacheroom.data.local.dao.NewsDao
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.mappers.mapResponseToEntity
import com.valentinerutto.datacacheroom.data.remote.Resource
import com.valentinerutto.datacacheroom.data.remote.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository(
    private val apiService: ApiService, private val newsDao: NewsDao
) {
    private val newsFlow: Flow<List<NewsEntity>>
        get() = newsDao.getNewsList()

    suspend fun getBreakingNews(): Flow<Resource<List<NewsEntity>>> = flow {
        emit(Resource.Loading())

//        try {
        newsFlow.collect {
            if (it.isNotEmpty()) {
                emit(Resource.Success(it))
            }
        }

        val remoteResponse = apiService.getBreakingNews()

        if (remoteResponse.isSuccessful.not()) {
            emit(
                Resource.Error("something went wrong: ${remoteResponse.message()}")
            )        }

        val newsDetailsList = mapResponseToEntity(remoteResponse.body()!!)!!

        newsDao.saveNewsList(newsDetailsList)


//        } catch (e: HttpException) {
//            emit(
//                Resource.Error("something went wrong: ${e.message()}")
//            )
//        } catch (e: IOException) {
//            emit(
//                Resource.Error("something went wrong:${e.message}")
//            )
//        }

    }

    private suspend fun fetchAndSaveNews() {
        val remoteResponse = apiService.getBreakingNews()
        if (remoteResponse.isSuccessful.not()) {
            newsDao.saveNewsList(mapResponseToEntity(remoteResponse.body()!!)!!)
        }
    }

    private fun getSavedNews(): Flow<List<NewsEntity>> {
        return flow { newsDao.getNewsList() }
    }

}