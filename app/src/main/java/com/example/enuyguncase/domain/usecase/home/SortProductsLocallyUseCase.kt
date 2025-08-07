package com.example.enuyguncase.domain.usecase.home

import com.example.enuyguncase.domain.model.Product
import javax.inject.Inject

class SortProductsLocallyUseCase @Inject constructor() {
    
    operator fun invoke(products: List<Product>, sortBy: String?, order: String?): List<Product> {
        if (sortBy == null) return products
        
        return when (sortBy) {
            "price" -> {
                if (order == "desc") {
                    products.sortedByDescending { it.price }
                } else {
                    products.sortedBy { it.price }
                }
            }
            "rating" -> {
                if (order == "desc") {
                    products.sortedByDescending { it.rating }
                } else {
                    products.sortedBy { it.rating }
                }
            }
            "discountPercentage" -> {
                if (order == "desc") {
                    products.sortedByDescending { it.discountPercentage }
                } else {
                    products.sortedBy { it.discountPercentage }
                }
            }
            "stock" -> {
                if (order == "desc") {
                    products.sortedByDescending { it.stock }
                } else {
                    products.sortedBy { it.stock }
                }
            }
            "title" -> {
                if (order == "desc") {
                    products.sortedByDescending { it.title.lowercase() }
                } else {
                    products.sortedBy { it.title.lowercase() }
                }
            }
            else -> products
        }
    }
} 