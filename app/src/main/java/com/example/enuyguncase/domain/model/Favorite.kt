package com.example.enuyguncase.domain.model


data class Favorite(
    val productId: Int,
    val title: String,
    val images: List<String>,
    val description: String,
    val price: Double,
    val discountedPrice: Double
) {
    val thumbnailUrl: String
        get() = images.firstOrNull().orEmpty()
}