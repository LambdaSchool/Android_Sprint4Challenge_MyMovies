package com.lambdaschool.datapersistencesprintchallenge.model

import android.app.Application
import androidx.lifecycle.LiveData




interface FavoriteMovieRepoInterface {



    fun createFavouriteMovie(favouriteMovie: FavoriteMovie)

    fun readAllFavouriteMovies(): LiveData<List<FavoriteMovie>>

    fun updateFavouriteMovie(favouriteMovie: FavoriteMovie)

    fun deleteFavouriteMovie(favouriteMovie: FavoriteMovie)



}