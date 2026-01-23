package com.vacral.shopapp.data.mappers

import com.vacral.shopapp.data.model.CartProductDto
import com.vacral.shopapp.data.model.ProductDto
import com.vacral.shopapp.domain.models.CartItem

fun CartItem.toDto(): CartProductDto{
    return CartProductDto(
        productId = this.product.id,
        quantity = this.quantity

    )
}