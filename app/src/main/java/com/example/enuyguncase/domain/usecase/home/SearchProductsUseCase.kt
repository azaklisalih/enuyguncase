package com.example.enuyguncase.domain.usecase.home

import com.example.enuyguncase.domain.model.Product
import com.example.enuyguncase.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(query: String): Flow<List<Product>> =
        repository.searchProductsFlow(query)
}