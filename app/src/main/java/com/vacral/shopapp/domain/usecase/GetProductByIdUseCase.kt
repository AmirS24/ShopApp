package com.vacral.shopapp.domain.usecase

import com.vacral.shopapp.domain.models.Product
import com.vacral.shopapp.domain.repository.ProductRepository

class GetProductByIdUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): Product {

      return repository.getProductById(productId = productId)
    }
}