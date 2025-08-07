package com.example.enuyguncase.presentation.favorite

import com.example.enuyguncase.domain.model.Favorite

data class FavoriteUIState(
    val favorites: List<Favorite> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)