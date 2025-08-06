package com.example.enuyguncase.domain.usecase.home

import com.example.enuyguncase.data.home.mapper.toDomainList
import com.example.enuyguncase.domain.model.ProductPage
import com.example.enuyguncase.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(
        category: String,
        limit: Int? = null,
        skip: Int? = null,
        sortBy: String? = null,
        order: String? = null
    ): Flow<ProductPage> =
        repository.getProductsByCategoryFlow(category, limit, skip, sortBy, order).map {
            ProductPage(it.toDomainList(), it.total)
        }
}