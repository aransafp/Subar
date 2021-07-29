package com.aransafp.core.domain.usecase

import com.aransafp.core.domain.model.Article
import com.aransafp.core.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow

class ArticleInteractor(private val articleRepository: IArticleRepository) : ArticleUseCase {

    override fun getAllArticle() = articleRepository.getAllArticle()

    override fun getFavoriteArticle() = articleRepository.getFavoriteArticle()

    override fun getArticle(articleId: Int): Flow<Article> = articleRepository.getArticle(articleId)


    override fun setFavoriteArticle(article: Article, state: Boolean) =
        articleRepository.setFavoriteArticle(article, state)

}