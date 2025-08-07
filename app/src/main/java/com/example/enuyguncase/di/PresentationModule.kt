package com.example.enuyguncase.di

import com.example.enuyguncase.presentation.common.navigation.NavigationCoordinator
import com.example.enuyguncase.presentation.common.navigation.NavigationRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

    @Binds
    @Singleton
    abstract fun bindNavigationRouter(
        navigationCoordinator: NavigationCoordinator
    ): NavigationRouter
} 