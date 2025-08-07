package com.example.enuyguncase.domain.repository

import com.example.enuyguncase.data.home.local.entities.CartItemEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItemEntity>>
    suspend fun addOrUpdate(item: CartItemEntity)
    suspend fun remove(productId: Int)
    suspend fun increase(productId: Int)
    suspend fun decrease(productId: Int)
    suspend fun getByProductId(productId: Int): CartItemEntity?
    suspend fun clearCart()
}