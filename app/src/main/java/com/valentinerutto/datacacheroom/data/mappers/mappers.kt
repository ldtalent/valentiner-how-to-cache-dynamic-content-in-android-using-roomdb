package com.valentinerutto.datacacheroom.data.mappers

import com.valentinerutto.datacacheroom.data.local.entities.NewsEntity
import com.valentinerutto.datacacheroom.data.remote.model.NewsArticle
import com.valentinerutto.datacacheroom.data.remote.model.NewsResponse
import com.valentinerutto.datacacheroom.utils.orUnknown
import kotlin.random.Random

fun mapResponseToEntity(newsResponse: NewsResponse): List<NewsEntity>? {
    return newsResponse.articles?.map { news ->
        NewsEntity(
            id = Random.nextInt(),
            author = news?.author.orUnknown("Author"),
            title = news?.title.orUnknown("Title"),
            description = news?.description.orUnknown("Description"),
            sourceUrl = news?.url.orUnknown(""),
            imageUrl = news?.urlToImage.orUnknown(""),
            publishedAt = news?.publishedAt.orUnknown("Published Date"),
            content = news?.content.orUnknown("Article Content")
        )
    }
}

fun mapEntitiytoNewsArticle(newsEntity: NewsEntity): NewsArticle {
    return NewsArticle(
        author = newsEntity.author,
        title = newsEntity.title,
        description = newsEntity.description.orUnknown(""),
        sourceUrl = newsEntity.sourceUrl,
        imageUrl = newsEntity.imageUrl,
        publishedAt = newsEntity.publishedAt,
        content = newsEntity.content
    )
}
