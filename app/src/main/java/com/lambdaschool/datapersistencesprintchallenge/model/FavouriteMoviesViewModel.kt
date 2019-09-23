package com.lambdaschool.datapersistencesprintchallenge.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.datapersistencesprintchallenge.apiaccess.repo


class FavouriteMoviesViewModel : ViewModel() {





    val favouriteMovies: LiveData<List<FavoriteMovie>> by lazy {

        readAllFavouriteMovies()

    }



    fun readAllFavouriteMovies() : LiveData<List<FavoriteMovie>> {

       return repo.readAllFavouriteMovies()

    }

    fun createFavouriteMovie(favouriteMovie: FavoriteMovie) {

      repo.createFavouriteMovie((favouriteMovie))

    }



    fun updateFavouriteMovie(favouriteMovie: FavoriteMovie) {

            repo.updateFavouriteMovie(favouriteMovie)

    }

    fun deleteFavouriteMovie(favouriteMovie: FavoriteMovie) {

     repo.deleteFavouriteMovie(favouriteMovie)

    }

}