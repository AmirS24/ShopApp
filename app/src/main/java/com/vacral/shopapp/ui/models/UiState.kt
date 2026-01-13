package com.vacral.shopapp.ui.models

import com.vacral.shopapp.domain.models.Product

sealed interface UiState<out T> {

    data object Loading : UiState<Nothing>

    data class Success<T>(val data: T) : UiState<T>

    data class Error(val messege: String) : UiState<Nothing>
}