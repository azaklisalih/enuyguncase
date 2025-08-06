package com.example.enuyguncase.domain.usecase.productdetail

import com.example.enuyguncase.domain.model.Product
import com.example.enuyguncase.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
private val repo: ProductRepository
) {
    operator fun invoke(id: Int): Flow<Product> = repo.getProductByIdFlow(id)

}