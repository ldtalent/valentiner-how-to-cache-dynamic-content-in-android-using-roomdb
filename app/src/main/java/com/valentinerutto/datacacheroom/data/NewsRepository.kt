package com.valentinerutto.datacacheroom.data

import com.valentinerutto.datacacheroom.data.local.DatabaseHelper
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.mappers.mapResponseToEntity
import com.valentinerutto.datacacheroom.data.remote.Resource
import com.valentinerutto.datacacheroom.data.remote.api.ApiService
import com.valentinerutto.datacacheroom.data.remote.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class NewsRepository(
    private val databaseHelper: DatabaseHelper, private val apiService: ApiService
) {

    suspend fun getNewsFromNetwork(): Flow<Resource<NewsResponse>> {
        return flow<Resource<NewsResponse>> {
            val response = apiService.getBreakingNews()
            // emit(Resource.Success(response.body()))
        }

    }

    //   suspend fun getBreakingNews(): Flow<Resource<List<NewsEntity>>> = flow {
    suspend fun getBreakingNews(): Flow<Resource<Response<NewsResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getBreakingNews()
            emit(Resource.Success(response))
//
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
//        databaseHelper.getSavedArticles().collect {
//            emit(Resource.Success(it))
//        }

    }

    private suspend fun fetchAndSaveNews() {
        val remoteResponse = apiService.getBreakingNews()
        databaseHelper.insertAll(mapResponseToEntity(remoteResponse.body()!!)!!)
    }

    suspend fun getSavedNews(): Flow<List<NewsEntity>> {
        return databaseHelper.getSavedArticles()
    }
}