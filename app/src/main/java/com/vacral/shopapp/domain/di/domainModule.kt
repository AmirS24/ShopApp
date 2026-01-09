package com.vacral.shopapp.domain.di

import com.vacral.shopapp.domain.usecase.GetProductByIdUseCase
import com.vacral.shopapp.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductUseCase(get ()) }
    factory { GetProductByIdUseCase(get()) }
}