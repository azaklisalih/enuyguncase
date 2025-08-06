package com.example.enuyguncase.presentation.productdetail

import com.example.enuyguncase.domain.model.Product

data class ProductDetailUIState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
