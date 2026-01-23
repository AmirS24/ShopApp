package com.vacral.shopapp.domain.usecase

import com.vacral.shopapp.domain.models.CartItem
import com.vacral.shopapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItemUseCase (
    private val repository : CartRepository
){
    operator fun invoke(): Flow<List<CartItem>> {
        return repository.cartItems
    }
}