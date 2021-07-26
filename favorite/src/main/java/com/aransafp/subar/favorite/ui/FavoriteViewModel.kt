package com.aransafp.subar.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aransafp.core.domain.usecase.ArticleUseCase

class FavoriteViewModel(articleUseCase: ArticleUseCase) : ViewModel() {
    val articles = articleUseCase.getFavoriteArticle().asLiveData()
}