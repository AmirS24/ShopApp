package com.vacral.shopapp.ui.fragments.product.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.vacral.shopapp.domain.models.Product
import com.vacral.shopapp.domain.usecase.GetProductByIdUseCase
import com.vacral.shopapp.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<Product>>(UiState.Loading)
    val state: StateFlow<UiState<Product>> = _state.asStateFlow()

    fun loadProduct(id: Int) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                _state.value = UiState.Success(getProductByIdUseCase(id))
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error")

            }

        }

    }

}