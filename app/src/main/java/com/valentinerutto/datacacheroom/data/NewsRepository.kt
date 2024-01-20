package com.valentinerutto.datacacheroom.data

import com.valentinerutto.datacacheroom.data.local.DatabaseHelper
import com.valentinerutto.datacacheroom.data.local.dao.NewsDao
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.mappers.mapResponseToEntity
import com.valentinerutto.datacacheroom.data.remote.Resource
import com.valentinerutto.datacacheroom.data.remote.api.ApiService
import com.valentinerutto.datacacheroom.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NewsRepository(
    private val databaseHelper: DatabaseHelper,
    private val apiService: ApiService
) {
    suspend fun getBreakingNews(): Flow<Resource<List<NewsEntity>>> = flow {
        emit(Resource.Loading())
        try {
            fetchAndSaveNews()
        } catch (e: HttpException) {
            emit(
                Resource.Error("something went wrong:")
            )
        } catch (e: IOException) {
            emit(
                Resource.Error("something went wrong with server:")
            )
        }
        databaseHelper.getSavedArticles().collect{
           emit( Resource.Success(it))
        }
    }

    private suspend fun fetchAndSaveNews() {
        val remoteResponse = apiService.getBreakingNews(Constants.API_KEY)
        databaseHelper.insertAll(mapResponseToEntity(remoteResponse.body()!!)!!)
    }

}