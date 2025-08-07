package com.example.enuyguncase.data.home.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.enuyguncase.data.common.converters.Converters
import com.example.enuyguncase.data.home.local.dao.CartDao
import com.example.enuyguncase.data.home.local.dao.FavoriteDao
import com.example.enuyguncase.data.home.local.entities.CartItemEntity
import com.example.enuyguncase.data.home.local.entities.FavoriteEntity

@Database(entities = [FavoriteEntity::class, CartItemEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun cartDao(): CartDao

}