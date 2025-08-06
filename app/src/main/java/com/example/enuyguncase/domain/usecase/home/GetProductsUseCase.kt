package com.example.enuyguncase.domain.usecase.home

import com.example.enuyguncase.domain.model.Product
import com.example.enuyguncase.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase @Inject constructor(
private val repository: ProductRepository
) {
    operator fun invoke(
        limit: Int? = null,
        skip: Int? = null,
        sortBy: String? = null,
        order: String? = null
    ): Flow<List<Product>> = repository.getProductsFlow(limit, skip, sortBy, order)
}