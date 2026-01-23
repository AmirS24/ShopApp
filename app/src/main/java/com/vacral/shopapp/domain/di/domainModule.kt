package com.vacral.shopapp.domain.di

import com.vacral.shopapp.domain.usecase.AddToCartUseCase
import com.vacral.shopapp.domain.usecase.CheckoutUseCase
import com.vacral.shopapp.domain.usecase.ClearCartUseCase
import com.vacral.shopapp.domain.usecase.GetCartItemUseCase
import com.vacral.shopapp.domain.usecase.GetProductByIdUseCase
import com.vacral.shopapp.domain.usecase.GetProductUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductUseCase(get ()) }
    factory { GetProductByIdUseCase(get()) }
    factoryOf(::GetCartItemUseCase)
    factoryOf(::CheckoutUseCase)
    factoryOf(::ClearCartUseCase)
    factoryOf(::AddToCartUseCase)


}