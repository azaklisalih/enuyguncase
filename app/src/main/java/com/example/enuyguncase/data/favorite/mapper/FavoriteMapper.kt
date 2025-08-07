package com.example.enuyguncase.data.favorite.mapper

import com.example.enuyguncase.data.home.local.entities.FavoriteEntity
import com.example.enuyguncase.domain.model.Favorite

fun FavoriteEntity.toDomainModel(): Favorite =
    Favorite(
        productId = this.productId,
        title = this.title,
        price = this.price,
        discountedPrice = this.discountedPrice,
        images = this.images,
        description = this.description
    )