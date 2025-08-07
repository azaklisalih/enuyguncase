package com.example.enuyguncase.domain.usecase.cart

import com.example.enuyguncase.domain.repository.CartRepository
import javax.inject.Inject

class DecreaseCartQuantityUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(productId: Int) {
        cartRepository.decrease(productId)
    }
}