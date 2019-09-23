package com.lambdaschool.datapersistencesprintchallenge.MovieDAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview




@Dao

interface FavoriteMovieDao {



    @Insert(onConflict = OnConflictStrategy.ABORT)

    fun createFavouriteMovie(favouriteMovie: FavoriteMovie)



    @Query("SELECT * FROM FavoriteMovie")

    fun readAllFavouriteMovies(): LiveData<List<FavoriteMovie>>



    @Update(onConflict = OnConflictStrategy.REPLACE)

    fun updateFavouriteMovie(favouriteMovie:FavoriteMovie)



    @Delete

    fun deleteFavouriteMovie(favouriteMovie: FavoriteMovie)

}