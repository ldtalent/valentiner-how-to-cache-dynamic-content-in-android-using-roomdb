package com.valentinerutto.datacacheroom.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Upsert
    suspend fun saveNewsList(newsList:NewsEntity)

    @Query("SELECT * FROM newslist ")
    suspend fun getNewsList():Flow<List<NewsEntity>>

}