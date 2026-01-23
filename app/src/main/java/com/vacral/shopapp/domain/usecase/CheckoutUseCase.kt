package com.vacral.shopapp.domain.usecase

import com.vacral.shopapp.domain.repository.CartRepository

class CheckoutUseCase (
    private val repository : CartRepository
){
   suspend operator fun invoke(): Result<String> {
        return repository.checkout()
    }
}