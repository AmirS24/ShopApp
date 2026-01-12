package com.vacral.shopapp.data.mappers

import com.vacral.shopapp.data.model.ProductDto
import com.vacral.shopapp.data.model.RatingDto
import com.vacral.shopapp.domain.models.Product
import com.vacral.shopapp.domain.models.Rating

fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id ?: -1,
        title = this.title ?: "",
        price = this.price ?: 0.0,
        description = this.description ?: "",
        category = this.category ?: "",
        image = this.image ?: "",
        rating = Rating(
            this.rating?.rate ?: 0.0,
            this.rating?.count ?: 0
        )
    )
}
