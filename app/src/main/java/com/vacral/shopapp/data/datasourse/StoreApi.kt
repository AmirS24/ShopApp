package com.vacral.shopapp.data.datasourse

import com.vacral.shopapp.data.model.CartRequestDto
import com.vacral.shopapp.data.model.CartResponseDto
import com.vacral.shopapp.data.model.ProductDto
import com.vacral.shopapp.domain.models.Product
import com.vacral.shopapp.domain.usecase.GetProductUseCase
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StoreApi {
    @GET("products")
    suspend fun getAllProducts(): List<ProductDto>

    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    @POST("carts")
    suspend fun checkout(@Body cart: CartRequestDto): CartResponseDto
}