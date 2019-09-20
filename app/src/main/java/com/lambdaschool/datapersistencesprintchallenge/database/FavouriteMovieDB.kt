package com.lambdaschool.datapersistencesprintchallenge.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.lambdaschool.datapersistencesprintchallenge.FavouriteMovie

@Database(entities = [FavouriteMovie::class], version = 2, exportSchema = false)
abstract class FavouriteMovieDB : RoomDatabase() {
    abstract fun favouriteMovieDao():FavouriteMovieDao
}