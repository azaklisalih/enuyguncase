package com.example.enuyguncase.domain.usecase.cart

import com.example.enuyguncase.domain.repository.CartRepository
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    /**
     * Checkout sürecini başlatır.
     */
    suspend operator fun invoke() {
    }
}