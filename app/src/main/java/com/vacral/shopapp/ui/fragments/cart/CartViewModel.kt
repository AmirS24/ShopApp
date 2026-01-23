package com.shabelnikd.shopapp.ui.fragments.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vacral.shopapp.domain.usecase.CheckoutUseCase
import com.vacral.shopapp.domain.usecase.ClearCartUseCase
import com.vacral.shopapp.domain.usecase.GetCartItemUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartItemsUseCase: GetCartItemUseCase,
    private val checkoutUseCase: CheckoutUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {

    val items = getCartItemsUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val total = items.map { list ->
        list.sumOf {
            it.product.price * it.quantity
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    fun checkout() {
        viewModelScope.launch {
            val result = checkoutUseCase()
            result
                .onSuccess { clearCartUseCase() }
        }
    }
}