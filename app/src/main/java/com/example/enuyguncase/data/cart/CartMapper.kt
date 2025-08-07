package com.example.enuyguncase.data.cart

import com.example.enuyguncase.data.home.local.entities.CartItemEntity
import com.example.enuyguncase.domain.model.CartItem


fun CartItemEntity.toDomainList(): CartItem = CartItem(
    productId, title, price, discountPrice, thumbnail, quantity
)