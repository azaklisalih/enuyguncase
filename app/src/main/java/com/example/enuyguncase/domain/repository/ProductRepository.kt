package com.example.enuyguncase.domain.repository

import com.example.enuyguncase.data.home.remote.dto.ProductsResponse
import com.example.enuyguncase.domain.model.Product
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepository {

    fun getProductsFlow(
        limit: Int? = null,
        skip: Int? = null,
        sortBy: String? = null,
        order: String? = null
    ): Flow<List<Product>>

    fun getProductByIdFlow(id: Int): Flow<Product>
    fun searchProductsFlow(query: String): Flow<List<Product>>
    fun getCategoriesFlow(): Flow<List<String>>
    fun getProductsByCategoryFlow(
        category: String,
        limit: Int? = null,
        skip: Int? = null
    ): Flow<List<Product>>
}