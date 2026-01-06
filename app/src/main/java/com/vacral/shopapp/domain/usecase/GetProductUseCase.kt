package com.vacral.shopapp.domain.usecase

import com.vacral.shopapp.domain.models.Product
import com.vacral.shopapp.domain.repository.ProductRepository

class GetProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun  invoke(): List<Product> = repository.getProducts()
}