package com.lambdaschool.datapersistencesprintchallenge.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavoriteMovie::class), version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}