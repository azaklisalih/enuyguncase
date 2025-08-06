package com.example.enuyguncase.domain.usecase.productdetail

import com.example.enuyguncase.domain.repository.FavoriteRepository
import javax.inject.Inject

class CheckFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(productId: Int): Boolean {
        return favoriteRepository.isFavorite(productId)
    }
}
