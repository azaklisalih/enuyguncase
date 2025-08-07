package com.example.enuyguncase.data.home.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.enuyguncase.data.home.local.entities.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items WHERE productId = :id")
    suspend fun getItemByProductId(id: Int): CartItemEntity?

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: CartItemEntity)

    @Query("DELETE FROM cart_items WHERE productId = :id")
    suspend fun deleteByProductId(id: Int)

    @Query("UPDATE cart_items SET quantity = quantity + 1 WHERE productId = :id")
    suspend fun increaseQuantity(id: Int)

    @Query("UPDATE cart_items SET quantity = quantity - 1 WHERE productId = :id AND quantity > 1")
    suspend fun decreaseQuantity(id: Int)
    
    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}