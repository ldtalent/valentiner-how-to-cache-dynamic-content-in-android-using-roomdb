package com.valentinerutto.datacacheroom.data

import androidx.lifecycle.asLiveData
import com.valentinerutto.datacacheroom.data.local.dao.NewsDao
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.mappers.mapResponseToEntity
import com.valentinerutto.datacacheroom.data.remote.Resource
import com.valentinerutto.datacacheroom.data.remote.api.ApiService
import com.valentinerutto.datacacheroom.utils.convertToDate
import com.valentinerutto.datacacheroom.utils.todayInDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate

class NewsRepository(
    private val apiService: ApiService, private val newsDao: NewsDao
) {
     val newsFlow: Flow<List<NewsEntity>>
        get() = newsDao.getNewsList()

    suspend fun getBreakingNews(): Flow<Resource<List<NewsEntity>>> = flow {
        emit(Resource.Loading())

        try {

        val remoteResponse = apiService.getBreakingNews()

        if (remoteResponse.isSuccessful.not()) {
            emit(
                Resource.Error("something went wrong: ${remoteResponse.message()}")
            )        }

        val newsDetailsList = mapResponseToEntity(remoteResponse.body()!!)!!

        newsDao.saveNewsList(newsDetailsList)

            newsFlow.collect {
                if (it.isNotEmpty()) {
                    emit(Resource.Success(it))
                }
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error("something went wrong: ${e.message()}")
            )
        } catch (e: IOException) {
            emit(
                Resource.Error("something went wrong:${e.message}")
            )
        }
//        newsFlow.collect {
//            if (it.isNotEmpty()) {
//                emit(Resource.Success(it))
//            }
//        }
    }

    private suspend fun refreshData() {
        val remoteResponse = apiService.getBreakingNews()
        var oldDate:Long= 0
        newsFlow.collect{
            it.map {
             oldDate=   it.publishedAt.toLong()
            }
        }

        if (remoteResponse.isSuccessful.not()) {
            if (oldDate.shouldSync()){
                newsDao.deleteAll()
                newsDao.saveNewsList(mapResponseToEntity(remoteResponse.body()!!)!!)
            }
        }
    }
    private fun Long.shouldSync(): Boolean {
        val today = todayInDate()
        val syncData = convertToDate(this)
        return syncData != today
    }



}