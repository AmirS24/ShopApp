package com.vacral.shopapp.data.repository

import com.vacral.shopapp.data.datasourse.StoreApi
import com.vacral.shopapp.data.mappers.toDomain
import com.vacral.shopapp.domain.models.Product
import com.vacral.shopapp.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val storeApi: StoreApi
): ProductRepository {

    override suspend fun getProducts(): List<Product> = storeApi.getAllProducts().map {
        productDto -> productDto.toDomain()
    }

    override suspend fun getProductById(productId: Int): Product {
        return storeApi.getProductById(productId).toDomain()

    }

}