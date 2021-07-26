package com.aransafp.core.data.source.local

import com.aransafp.core.data.source.local.entity.ArticleEntity
import com.aransafp.core.data.source.local.room.SubarDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val subarDao: SubarDao) {

    fun getAllArticle(): Flow<List<ArticleEntity>> = subarDao.getAllArticle()

    fun getFavoriteArticle(): Flow<List<ArticleEntity>> = subarDao.getFavoriteArticle()

    suspend fun insertArticle(listArticle: List<ArticleEntity>) =
        subarDao.insertArticle(listArticle)

    fun setFavoriteArticle(article: ArticleEntity, newState: Boolean) {
        article.isFavorite = newState
        subarDao.updateFavoriteArticle(article)
    }
}