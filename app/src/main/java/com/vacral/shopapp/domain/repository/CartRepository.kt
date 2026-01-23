package com.vacral.shopapp.domain.repository

import com.vacral.shopapp.domain.models.CartItem
import com.vacral.shopapp.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    val cartItems: Flow<List<CartItem>>

    suspend fun addToCart(product: Product)
    suspend fun checkout(): Result<String>
    suspend fun clearCart()


}