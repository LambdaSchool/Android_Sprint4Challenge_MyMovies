package com.lambdaschool.datapersistencesprintchallenge
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class FavouriteMoviesViewModel : ViewModel() {


    val favouriteMovies: LiveData<List<FavouriteMovie>> by lazy {
        readAllFavouriteMovies()
    }

    fun readAllFavouriteMovies() : LiveData<List<FavouriteMovie>> {
        return repo.readAllFavouriteMovies()
    }

    fun createFavouriteMovie(favouriteMovie: FavouriteMovie) {
        repo.createFavouriteMovie((favouriteMovie))
    }

    fun updateFavouriteMovie(favouriteMovie: FavouriteMovie) {
        repo.updateFavouriteMovie(favouriteMovie)
    }
}