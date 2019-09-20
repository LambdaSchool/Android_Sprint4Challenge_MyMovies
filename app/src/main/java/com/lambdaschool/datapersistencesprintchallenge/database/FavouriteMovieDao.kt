package com.lambdaschool.datapersistencesprintchallenge.database
import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.datapersistencesprintchallenge.FavouriteMovie

@Dao
interface FavouriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createFavouriteMovie(favouriteMovie: FavouriteMovie)

    @Query("SELECT * FROM FavouriteMovie")
    fun readAllFavouriteMovies(): LiveData<List<FavouriteMovie>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavouriteMovie(favouriteMovie:FavouriteMovie)

    @Delete
    fun deleteFavouriteMovie(favouriteMovie: FavouriteMovie)
}