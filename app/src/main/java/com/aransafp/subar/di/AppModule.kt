package com.aransafp.subar.di

import com.aransafp.core.domain.usecase.ArticleInteractor
import com.aransafp.core.domain.usecase.ArticleUseCase
import com.aransafp.subar.detail.DetailViewModel
import com.aransafp.subar.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ArticleUseCase> { ArticleInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}