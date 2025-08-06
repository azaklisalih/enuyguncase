package com.example.enuyguncase.presentation.home

import com.example.enuyguncase.domain.model.Product

data class HomeUIState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
