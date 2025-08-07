package com.example.enuyguncase.domain.usecase.favorite

import com.example.enuyguncase.data.favorite.mapper.toDomainModel
import com.example.enuyguncase.domain.model.Favorite
import com.example.enuyguncase.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val favoriteRepo: FavoriteRepository
) {
    operator fun invoke(): Flow<List<Favorite>> = favoriteRepo.getFavorites()
        .map { list ->
            list.map { entity ->
                entity.toDomainModel()
            }
        }

}


