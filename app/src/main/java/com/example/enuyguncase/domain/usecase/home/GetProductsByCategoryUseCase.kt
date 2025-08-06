package com.example.enuyguncase.domain.usecase.home

import com.example.enuyguncase.domain.model.Product
import com.example.enuyguncase.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(
        category: String,
        limit: Int? = null,
        skip: Int? = null
    ): Flow<List<Product>> =
        repository.getProductsByCategoryFlow(category, limit, skip)
}