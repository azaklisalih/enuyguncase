package com.example.enuyguncase.domain.usecase.productdetail

import com.example.enuyguncase.domain.repository.FavoriteRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(productId: Int) {
        favoriteRepository.removeFavorite(productId)
    }
}