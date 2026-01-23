package com.vacral.shopapp.domain.usecase

import com.vacral.shopapp.domain.repository.CartRepository

class ClearCartUseCase(
    private val repository : CartRepository
){
    suspend operator fun invoke(){
        return repository.clearCart()
    }
}