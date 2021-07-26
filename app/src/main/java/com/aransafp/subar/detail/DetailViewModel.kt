package com.aransafp.subar.detail

import androidx.lifecycle.ViewModel
import com.aransafp.core.domain.model.Article
import com.aransafp.core.domain.usecase.ArticleUseCase

class DetailViewModel(private val articleUseCase: ArticleUseCase) : ViewModel() {
    fun setFavoriteArticle(article: Article, newStatus: Boolean) =
        articleUseCase.setFavoriteArticle(article, newStatus)
}