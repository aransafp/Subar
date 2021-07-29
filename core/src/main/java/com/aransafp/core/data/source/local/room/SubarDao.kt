package com.aransafp.core.data.source.local.room

import androidx.room.*
import com.aransafp.core.data.source.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubarDao {

    @Query("SELECT * FROM article")
    fun getAllArticle(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM article WHERE isFavorite = 1")
    fun getFavoriteArticle(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM article WHERE id = :articleId")
    fun getArticle(articleId: Int): Flow<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: List<ArticleEntity>)

    @Update
    fun updateFavoriteArticle(article: ArticleEntity)
}