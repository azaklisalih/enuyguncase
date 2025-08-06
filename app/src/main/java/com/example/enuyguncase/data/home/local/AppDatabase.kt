package com.example.enuyguncase.data.home.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.enuyguncase.data.home.local.dao.FavoriteDao
import com.example.enuyguncase.data.home.local.entities.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

}