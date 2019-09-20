package com.lambdaschool.datapersistencesprintchallenge.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview

class DataBaseBuilder(context: Context) {
    companion object {
        fun addToFavoriteList(movie: FavoriteMovie){
            AddMovieTask().execute(movie)
        }
    }


    val db = Room.databaseBuilder(
        context.applicationContext,
        MovieDataBase::class.java,
        "favorite-movie-list"
    ).build()




     class AddMovieTask: AsyncTask<FavoriteMovie, Void, Unit>(){
         override fun doInBackground(vararg movie: FavoriteMovie?) {
             App.createDataBase?.db?.movieDao()?.addFavMovie(movie[0]!!)
         }
     }

    /*class GetMoviesTask: AsyncTask<Void, Void, List<FavoriteMovie>>(){
        override fun doInBackground(vararg p0: Void?): List<FavoriteMovie> {
            return
        }

        override fun onPostExecute(result: List<FavoriteMovie>?) {
            super.onPostExecute(result)
            return result
        }

    }*/



}