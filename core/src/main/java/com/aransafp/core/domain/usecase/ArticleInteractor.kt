package com.aransafp.core.domain.usecase

import com.aransafp.core.domain.model.Article
import com.aransafp.core.domain.repository.IArticleRepository

class ArticleInteractor(private val articleRepository: IArticleRepository) : ArticleUseCase {

    override fun getAllArticle() = articleRepository.getAllArticle()

    override fun getFavoriteArticle() = articleRepository.getFavoriteArticle()

    override fun setFavoriteArticle(article: Article, state: Boolean) =
        articleRepository.setFavoriteArticle(article, state)

}