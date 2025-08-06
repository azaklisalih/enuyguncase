package com.example.enuyguncase.domain.repository

import com.example.enuyguncase.data.home.local.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)
    suspend fun removeFavorite(productId: Int)
    fun getFavorites(): Flow<List<FavoriteEntity>>
    suspend fun isFavorite(productId: Int): Boolean
}