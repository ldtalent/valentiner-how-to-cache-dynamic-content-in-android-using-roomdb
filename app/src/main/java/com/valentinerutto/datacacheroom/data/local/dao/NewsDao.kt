package com.valentinerutto.datacacheroom.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.remote.model.NewsArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    //@Upsert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun saveNewsList(newsList:List<NewsEntity>?)

    @Query("SELECT * FROM newslist ")
     fun getNewsList():List<NewsEntity>

     @Delete
     fun deleteBookmark(newsEntity: NewsEntity)

}