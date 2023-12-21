package com.valentinerutto.datacacheroom.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.valentinerutto.datacacheroom.data.local.dao.NewsDao
import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity

@Database(exportSchema = false, version = 1,
    entities = [NewsEntity::class])
abstract class NewsDatabase :RoomDatabase(){
    abstract val newsDao: NewsDao
}