package com.lambdaschool.datapersistencesprintchallenge

import androidx.lifecycle.LiveData

interface FavouriteMovieRepoInterface {

    fun createFavouriteMovie(favouriteMovie: FavouriteMovie)
    fun readAllFavouriteMovies(): LiveData<List<FavouriteMovie>>
    fun updateFavouriteMovie(favouriteMovie: FavouriteMovie)
    fun deleteFavouriteMovie(favouriteMovie: FavouriteMovie)

}