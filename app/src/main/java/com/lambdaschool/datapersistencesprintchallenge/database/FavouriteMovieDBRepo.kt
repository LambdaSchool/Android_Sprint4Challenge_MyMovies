package com.lambdaschool.datapersistencesprintchallenge.database
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lambdaschool.datapersistencesprintchallenge.FavouriteMovie
import com.lambdaschool.datapersistencesprintchallenge.FavouriteMovieRepoInterface

class FavouriteMovieDBRepo(val context: Context) : FavouriteMovieRepoInterface {

    override fun createFavouriteMovie(favouriteMovie: FavouriteMovie) {
        database.favouriteMovieDao().createFavouriteMovie(favouriteMovie)
    }

    override fun readAllFavouriteMovies(): LiveData<List<FavouriteMovie>> {
        return database.favouriteMovieDao().readAllFavouriteMovies()
    }

    override fun updateFavouriteMovie(favouriteMovie: FavouriteMovie) {
        database.favouriteMovieDao().updateFavouriteMovie(favouriteMovie)
    }

    override fun deleteFavouriteMovie(favouriteMovie: FavouriteMovie) {
        database.favouriteMovieDao().deleteFavouriteMovie(favouriteMovie)
    }

    private val database/**/ by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            FavouriteMovieDB::class.java, "favouriteMovie_database"
        ).fallbackToDestructiveMigration().build()
    }
}