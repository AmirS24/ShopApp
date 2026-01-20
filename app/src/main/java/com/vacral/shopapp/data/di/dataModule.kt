package com.vacral.shopapp.data.di

import com.vacral.shopapp.data.datasourse.StoreApi
import com.vacral.shopapp.data.repository.ProductRepositoryImpl
import com.vacral.shopapp.domain.repository.ProductRepository
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlin.math.sin

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    prettyPrint = true
}

private const val BASE_URL = "https://fakestoreapi.com/"

val dataModule = module {

    single<Converter.Factory> {
        json.asConverterFactory("applycation/json".toMediaType())
    }

    single<Interceptor>(named("logging")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named("logging"))).build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
            .create(StoreApi::class.java)
    }

    single<ProductRepository> { ProductRepositoryImpl(get()) }

}