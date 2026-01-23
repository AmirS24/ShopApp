package com.vacral.shopapp.ui.fragments.product


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vacral.shopapp.domain.models.Product
import com.vacral.shopapp.domain.usecase.AddToCartUseCase
import com.vacral.shopapp.domain.usecase.GetProductUseCase
import com.vacral.shopapp.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Product>>> = _state.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val products = getProductUseCase()
                _state.value = UiState.Success(products)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error")
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            addToCartUseCase(product)
        }
    }
}