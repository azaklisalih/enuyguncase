package com.example.enuyguncase.domain.usecase.cart

import com.example.enuyguncase.data.cart.toDomainList
import com.example.enuyguncase.domain.model.CartItem
import com.example.enuyguncase.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
private val cartRepository: CartRepository
) {

    operator fun invoke(): Flow<List<CartItem>> =
        cartRepository.getCartItems().map {
            it.map { cartItemEntity ->
                cartItemEntity.toDomainList()
            }
        }
}