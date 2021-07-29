package com.aransafp.subar.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aransafp.core.domain.model.Article
import com.aransafp.core.domain.usecase.ArticleUseCase

class DetailViewModel(private val articleUseCase: ArticleUseCase) : ViewModel() {

    fun getArticle(articleId: Int): LiveData<Article> =
        articleUseCase.getArticle(articleId).asLiveData()

    fun setFavoriteArticle(article: Article, newStatus: Boolean) =
        articleUseCase.setFavoriteArticle(article, newStatus)
}