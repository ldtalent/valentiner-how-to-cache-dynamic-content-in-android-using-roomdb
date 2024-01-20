package com.valentinerutto.datacacheroom.data.local

import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatabaseHelperImpl(private val database: NewsDatabase) : DatabaseHelper {
    override fun insertAll(newsEntity: List<NewsEntity>): Flow<Unit> = flow {
        database.newsDao.saveNewsList(newsEntity)
        emit(Unit)
    }

    override fun getSavedArticles(): Flow<List<NewsEntity>> = flow {
        emit(database.newsDao.getNewsList())
    }

    override suspend fun deleteArticle(newsEntity: NewsEntity) {
        database.newsDao.deleteBookmark(newsEntity)
    }

    override fun deleteAllAndInsertAll(newsEntity: List<NewsEntity>) {
        //
    }


}