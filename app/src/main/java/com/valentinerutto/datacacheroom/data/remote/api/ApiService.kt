package com.valentinerutto.datacacheroom.data.remote.api

import com.valentinerutto.datacacheroom.data.remote.model.NewsResponse
import com.valentinerutto.datacacheroom.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("sources")
        sources: String = "bbc-news",
        @Query("page")
        pageNumber: Int = 1,
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 100,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

}