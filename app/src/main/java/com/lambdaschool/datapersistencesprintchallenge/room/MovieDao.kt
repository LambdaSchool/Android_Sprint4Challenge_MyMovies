package com.lambdaschool.datapersistencesprintchallenge.room

import androidx.room.*
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview

@Dao
interface MovieDao {
    @Query("SELECT * FROM FavoriteMovie")
    fun getAllFavMovies(): List<FavoriteMovie>

    @Insert
    fun addFavMovie(movie: FavoriteMovie)

    @Query("DELETE FROM FavoriteMovie WHERE id = :id")
    fun deleteFavMovie(id: Int)
}