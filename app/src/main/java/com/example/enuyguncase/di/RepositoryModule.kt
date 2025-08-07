package com.example.enuyguncase.di

import com.example.enuyguncase.data.home.repository.CartRepositoryImpl
import com.example.enuyguncase.data.home.repository.FavoriteRepositoryImpl
import com.example.enuyguncase.data.home.repository.ProductRepositoryImpl
import com.example.enuyguncase.domain.repository.CartRepository
import com.example.enuyguncase.domain.repository.FavoriteRepository
import com.example.enuyguncase.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        impl: FavoriteRepositoryImpl
    ): FavoriteRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        impl: CartRepositoryImpl
    ): CartRepository
}
