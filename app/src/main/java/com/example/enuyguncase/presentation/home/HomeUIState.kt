package com.example.enuyguncase.presentation.home

import com.example.enuyguncase.domain.model.Product

data class HomeUIState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val total: Int = 0,
    val page: Int = 0,
    val pageSize: Int = 30,

    val selectedCategory: String? = null,
    val selectedSortBy: String? = null,
    val selectedSortOrder: String? = null
)
