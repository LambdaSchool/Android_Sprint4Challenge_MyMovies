package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lambdaschool.datapersistencesprintchallenge.MovieDAO.FavoriteMovieDao


@Database(entities = [FavoriteMovie::class], version = 2, exportSchema = false)

abstract class FavouriteMovieDB : RoomDatabase() {

    abstract fun favouriteMovieDao(): FavoriteMovieDao

}