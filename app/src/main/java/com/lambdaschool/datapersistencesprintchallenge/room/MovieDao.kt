package com.lambdaschool.datapersistencesprintchallenge.room

import androidx.room.*
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview

@Dao
interface MovieDao {
    @Query("SELECT * FROM FavoriteMovie")
    fun getAllFavMovies(): List<FavoriteMovie>

    @Insert()
    fun addFavMovie(movie: FavoriteMovie)

    @Delete
    fun deleteFavMovie(movie: FavoriteMovie)
}