package com.example.enuyguncase.data.home.repository

import com.example.enuyguncase.data.home.local.dao.FavoriteDao
import com.example.enuyguncase.data.home.local.entities.FavoriteEntity
import com.example.enuyguncase.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) {
        favoriteDao.insert(
            FavoriteEntity(
                productId = favoriteEntity.productId,
                title = favoriteEntity.title,
                thumbnail = favoriteEntity.thumbnail,
                description = favoriteEntity.description,
                price = favoriteEntity.price,
                discountedPrice = favoriteEntity.discountedPrice
            )
        )
    }

    override suspend fun removeFavorite(productId: Int) = favoriteDao.deleteById(productId)

    override fun getFavorites(): Flow<List<FavoriteEntity>> = favoriteDao.getAll()

    override suspend fun isFavorite(productId: Int): Boolean = favoriteDao.countById(productId) > 0

}