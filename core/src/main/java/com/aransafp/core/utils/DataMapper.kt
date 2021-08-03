package com.aransafp.core.utils

import com.aransafp.core.data.source.local.entity.ArticleEntity
import com.aransafp.core.data.source.remote.response.ArticleResponse
import com.aransafp.core.domain.model.Article

object DataMapper {

    fun mapResponsesToEntities(input: List<ArticleResponse>): List<ArticleEntity> {
        val articleList = ArrayList<ArticleEntity>()
        input.map {
            val article = ArticleEntity(
                id = 0,
                title = it.title,
                description = it.description,
                author = it.author,
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
                id = it.id,
                title = it.title,
                description = it.description,
                author = it.author,
                content = it.content,
                publishedAt = it.publishedAt,
                urlToImage = it.urlToImage,
                url = it.url,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Article) = ArticleEntity(
        id = input.id,
        title = input.title,
        description = input.description,
        author = input.author,
        content = input.content,
        publishedAt = input.publishedAt,
        urlToImage = input.urlToImage,
        url = input.url,
        isFavorite = input.isFavorite
    )

    fun mapEntityToDomain(input: ArticleEntity) = Article(
        id = input.id,
        title = input.title,
        description = input.description,
        author = input.author,
        content = input.content,
        publishedAt = input.publishedAt,
        urlToImage = input.urlToImage,
        url = input.url,
        isFavorite = input.isFavorite
    )

}