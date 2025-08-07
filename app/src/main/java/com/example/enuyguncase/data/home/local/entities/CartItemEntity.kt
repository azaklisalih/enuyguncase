package com.example.enuyguncase.data.home.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val productId: Int,
    val title: String,
    val price: Double,
    val discountPrice: Double,
    val thumbnail: String,
    val quantity: Int
)
