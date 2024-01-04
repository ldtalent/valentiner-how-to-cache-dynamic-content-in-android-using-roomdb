package com.valentinerutto.datacacheroom.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.valentinerutto.datacacheroom.data.remote.model.NewsArticle

@Entity(tableName = "NewsList")
data class NewsEntity(
    @PrimaryKey
    val id: Int,
    val author: String,
    val title: String,
    val description: String,
    val sourceUrl: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String
)