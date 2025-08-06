package com.example.enuyguncase.domain.usecase.home

import com.example.enuyguncase.data.home.mapper.toDomainList
import com.example.enuyguncase.domain.model.ProductPage
import com.example.enuyguncase.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(query: String): Flow<ProductPage> =
        repository.searchProductsFlow(query).map {
            ProductPage(
                products = it.toDomainList(),
                total = it.total
            )
        }
}