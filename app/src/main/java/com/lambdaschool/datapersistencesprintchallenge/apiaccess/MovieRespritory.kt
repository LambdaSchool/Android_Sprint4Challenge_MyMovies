package com.lambdaschool.datapersistencesprintchallenge.apiaccess

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovieRepoInterface

import com.lambdaschool.datapersistencesprintchallenge.model.FavouriteMovieDB



class MovieRespritory(val context: Context) : FavoriteMovieRepoInterface {



    override fun createFavouriteMovie(favouriteMovie: FavoriteMovie) {

        database.favouriteMovieDao().createFavouriteMovie(favouriteMovie)

    }



    override fun readAllFavouriteMovies(): LiveData<List<FavoriteMovie>> {

        return database.favouriteMovieDao().readAllFavouriteMovies()

    }



    override fun updateFavouriteMovie(favouriteMovie: FavoriteMovie) {

        database.favouriteMovieDao().updateFavouriteMovie(favouriteMovie)

    }



    override fun deleteFavouriteMovie(favouriteMovie: FavoriteMovie) {

        database.favouriteMovieDao().deleteFavouriteMovie(favouriteMovie)

    }



    private val database/**/ by lazy {

        Room.databaseBuilder(

            context.applicationContext,

            FavouriteMovieDB::class.java, "favouriteMovie_database"

        ).fallbackToDestructiveMigration().build()

    }

}