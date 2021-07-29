package com.aransafp.core.domain.usecase

import com.aransafp.core.data.Resource
import com.aransafp.core.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleUseCase {

    fun getAllArticle(): Flow<Resource<List<Article>>>

    fun getFavoriteArticle(): Flow<List<Article>>

    fun getArticle(articleId: Int): Flow<Article>

    fun setFavoriteArticle(article: Article, state: Boolean)
}