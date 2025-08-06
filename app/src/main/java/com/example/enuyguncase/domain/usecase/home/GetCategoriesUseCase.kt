package com.example.enuyguncase.domain.usecase.home
import com.example.enuyguncase.domain.model.Category
import kotlinx.coroutines.flow.Flow
import com.example.enuyguncase.domain.repository.ProductRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Category>> =
        repository.getCategoriesFlow()
}
