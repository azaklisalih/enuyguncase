package com.example.enuyguncase.data.home.remote.api

import com.example.enuyguncase.data.home.remote.dto.CategoryDto
import com.example.enuyguncase.data.home.remote.dto.ProductDto
import com.example.enuyguncase.data.home.remote.dto.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProducts(
        @Query("limit")  limit: Int?   = null,
        @Query("skip")   skip: Int?    = null,
            @Query("sortBy") sortBy: String? = null,
    @Query("order")  order: String?   = null
    ): Response<ProductsResponse>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Response<ProductDto>

    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String
    ): Response<ProductsResponse>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<CategoryDto>>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String,
        @Query("limit") limit: Int? = null,
        @Query("skip")  skip: Int?  = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("order") order: String? = null
    ): Response<ProductsResponse>

}