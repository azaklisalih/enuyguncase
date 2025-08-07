package com.example.enuyguncase.presentation.cart

import com.example.enuyguncase.domain.model.CartItem

data class CartUIState(
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
) 