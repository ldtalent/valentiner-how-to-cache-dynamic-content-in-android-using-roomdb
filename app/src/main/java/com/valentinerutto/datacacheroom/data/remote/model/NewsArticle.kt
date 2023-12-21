package com.valentinerutto.datacacheroom.data.remote.model

data class NewsArticle(
    val author: String,
    val title: String,
    val description: String,
    val sourceUrl: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String
)
