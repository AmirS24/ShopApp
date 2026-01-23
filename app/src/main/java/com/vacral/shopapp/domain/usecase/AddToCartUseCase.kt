package com.vacral.shopapp.domain.usecase

import com.vacral.shopapp.domain.models.Product
import com.vacral.shopapp.domain.repository.CartRepository

class AddToCartUseCase (
    private val repository : CartRepository
){
  suspend operator fun invoke(product: Product) {
        return repository.addToCart(product)
    }
}