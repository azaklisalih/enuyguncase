package com.example.enuyguncase.domain.usecase.productdetail

import com.example.enuyguncase.data.home.local.entities.FavoriteEntity
import com.example.enuyguncase.domain.model.Product
import com.example.enuyguncase.domain.repository.FavoriteRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val favRepo: FavoriteRepository
) {
    suspend operator fun invoke(product: Product) {
        favRepo.addFavorite(
            FavoriteEntity(
                productId = product.id,
                title = product.title,
                thumbnail = product.thumbnail,
                description = product.description,
                price = product.price,
                discountedPrice = product.discountedPrice
            )
        )
    }
}