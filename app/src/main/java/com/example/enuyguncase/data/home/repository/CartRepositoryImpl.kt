package com.example.enuyguncase.data.home.repository

import com.example.enuyguncase.data.home.local.dao.CartDao
import com.example.enuyguncase.data.home.local.entities.CartItemEntity
import com.example.enuyguncase.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val dao: CartDao
): CartRepository {
    override fun getCartItems() = dao.getAllCartItems()
    override suspend fun addOrUpdate(item: CartItemEntity) = dao.upsert(item)
    override suspend fun remove(productId: Int) = dao.deleteByProductId(productId)
    override suspend fun increase(productId: Int) = dao.increaseQuantity(productId)
    override suspend fun decrease(productId: Int) = dao.decreaseQuantity(productId)
    override suspend fun getByProductId(productId: Int) = dao.getItemByProductId(productId)

}