package com.vacral.shopapp.domain.repository

import com.vacral.shopapp.domain.models.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun getProductById(productId: Int): Product
}