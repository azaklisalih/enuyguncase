package com.example.enuyguncase.domain.repository

import com.example.enuyguncase.data.home.remote.dto.ProductsResponse
import com.example.enuyguncase.domain.model.Category
import com.example.enuyguncase.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProductsFlow(
        limit: Int? = null,
        skip: Int? = null,
        sortBy: String? = null,
        order: String? = null
    ): Flow<ProductsResponse>

    fun getProductByIdFlow(id: Int): Flow<Product>
    fun searchProductsFlow(query: String): Flow<ProductsResponse>
    fun getCategoriesFlow(): Flow<List<Category>>
    fun getProductsByCategoryFlow(
        category: String,
        limit: Int? = null,
        skip: Int? = null,
        sortBy: String? = null,
        order: String? = null
    ): Flow<ProductsResponse>
}