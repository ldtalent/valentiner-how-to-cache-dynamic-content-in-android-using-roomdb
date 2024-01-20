package com.valentinerutto.datacacheroom.data.local

import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {
     fun insertAll(newsEntity: List<NewsEntity>):Flow<Unit>
    fun getSavedArticles(): Flow<List<NewsEntity>>
     suspend fun deleteArticle(newsEntity: NewsEntity)
     fun deleteAllAndInsertAll(newsEntity: List<NewsEntity>)
}