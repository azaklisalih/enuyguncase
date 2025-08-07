package com.example.enuyguncase.domain.usecase.cart

import com.example.enuyguncase.domain.repository.CartRepository
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke() {
        repository.clearCart()
    }
} 