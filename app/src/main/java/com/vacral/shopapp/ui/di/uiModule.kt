package com.vacral.shopapp.ui.di

import com.vacral.shopapp.ui.fragments.product.ListViewModel
import com.vacral.shopapp.ui.fragments.product.detail.DetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.viewmodel.scope.viewModelScope

val uiModule = module {
    viewModel { ListViewModel(get()) }
    viewModelOf(::DetailViewModel)
}