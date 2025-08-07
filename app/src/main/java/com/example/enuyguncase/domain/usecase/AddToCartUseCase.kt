package com.example.enuyguncase.domain.usecase

import com.example.enuyguncase.data.home.local.entities.CartItemEntity
import com.example.enuyguncase.domain.model.CartItem
import com.example.enuyguncase.domain.repository.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(cartItem: CartItem) {
        val existing = cartRepository.getByProductId(cartItem.productId)
        val newQty = (existing?.quantity ?: 0) + 1
        cartRepository.addOrUpdate(CartItemEntity(
            productId = cartItem.productId,
            title = cartItem.title,
            price = cartItem.price,
            discountPrice = cartItem.discountPrice,
            thumbnail = cartItem.thumbnail,
            quantity = newQty
        ))
    }

}