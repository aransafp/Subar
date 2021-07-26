package com.aransafp.core.domain.repository

import com.aransafp.core.data.Resource
import com.aransafp.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {

    fun getAllArticle(): Flow<Resource<List<Article>>>

    fun getFavoriteArticle(): Flow<List<Article>>

    fun setFavoriteArticle(article: Article, state: Boolean)
}