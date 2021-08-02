package com.aransafp.subar.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aransafp.core.domain.usecase.ArticleUseCase

class HomeViewModel(articleUseCase: ArticleUseCase) : ViewModel() {
    val articles = articleUseCase.getAllArticle().asLiveData()

}