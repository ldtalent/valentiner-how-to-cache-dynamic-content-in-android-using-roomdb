package com.valentinerutto.datacacheroom.data.mappers

import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.remote.model.NewsResponse
import kotlin.random.Random

fun mapResponseToEntity(newsResponse: NewsResponse): List<NewsEntity>? {
    return newsResponse.articles?.map { news ->
        NewsEntity(
            id = Random.nextInt(),
            author = news?.author!!,
            title = news.title!!,
            description = news.description!!,
            sourceUrl = news.url!!,
            imageUrl = news.urlToImage!!,
            publishedAt = news.publishedAt!!,
            content = news.content!!
        )
    }
}else emptyList<NewsEntity>()
}