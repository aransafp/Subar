package com.aransafp.core.utils

import com.aransafp.core.data.source.local.entity.ArticleEntity
import com.aransafp.core.data.source.remote.response.ArticleResponse
import com.aransafp.core.domain.model.Article

object DataMapper {

    fun mapResponsesToEntities(input: List<ArticleResponse>): List<ArticleEntity> {
        val articleList = ArrayList<ArticleEntity>()
        input.map {
            val article = ArticleEntity(
                title = it.title,
                description = it.description,
                author = it.description,
                content = it.content,
                publishedAt = it.publishedAt,
                urlToImage = it.urlToImage,
                url = it.url,
                isFavorite = false
            )
            articleList.add(article)
        }
        return articleList
    }

    fun mapEntitiesToDomain(input: List<ArticleEntity>): List<Article> =
        input.map {
            Article(
                title = it.title,
                description = it.description,
                author = it.description,
                content = it.content,
                publishedAt = it.publishedAt,
                urlToImage = it.urlToImage,
                url = it.url,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Article) = ArticleEntity(
        title = input.title,
        description = input.description,
        author = input.description,
        content = input.content,
        publishedAt = input.publishedAt,
        urlToImage = input.urlToImage,
        url = input.url,
        isFavorite = input.isFavorite
    )
}