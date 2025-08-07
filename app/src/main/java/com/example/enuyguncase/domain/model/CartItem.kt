package com.example.enuyguncase.domain.model

import androidx.room.PrimaryKey

data class CartItem(
    val productId: Int,
    val title: String,
    val price: Double,
    val discountPrice: Double,
    val thumbnail: String,
    val quantity: Int
)
