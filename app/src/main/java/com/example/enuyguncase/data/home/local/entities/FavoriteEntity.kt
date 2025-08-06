package com.example.enuyguncase.data.home.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val productId: Int,
    val title: String? = null,
    val thumbnail: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val discountedPrice: Double? = null,
)
