package com.lambdaschool.datapersistencesprintchallenge.room

import android.content.Context
import android.net.sip.SipSession
import android.os.AsyncTask
import androidx.room.Room
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.view.FavoriteMovieList.FavoriteMovies


class DataBaseBuilder(context: Context) {
    companion object {

        val favoriteListOfMovies = ArrayList<FavoriteMovie>()


        fun addToFavoriteList(movie: FavoriteMovie){
            AddMovieTask().execute(movie)
        }

        fun removeToFavoriteList(movie: FavoriteMovie){
            RemoveMovieTask().execute(movie)
        }

        fun getAllFavoriteMovies(context: Context){
            GetMoviesTask(context).execute()
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
             val i = 0
         }
     }

    class RemoveMovieTask: AsyncTask<FavoriteMovie, Void, Unit>(){
        override fun doInBackground(vararg movie: FavoriteMovie?) {
            App.createDataBase?.db?.movieDao()?.deleteFavMovie(movie[0]!!)
            val i = 0
        }
    }

    class GetMoviesTask(context: Context): AsyncTask<Void, Void, List<FavoriteMovie>>(){

        var recyclerCallBack: UpdateRecycler? = null

        init {
            if (context is UpdateRecycler){
                recyclerCallBack = context
            }
        }

        override fun doInBackground(vararg p0: Void?): List<FavoriteMovie> {
            return App.createDataBase?.db?.movieDao()?.getAllFavMovies()!!
        }


        override fun onPostExecute(result: List<FavoriteMovie>?) {
            super.onPostExecute(result)
            result?.forEach {
                favoriteListOfMovies.add(it)
            }
            recyclerCallBack?.update()

        }

    }

    interface UpdateRecycler{
        fun update()
    }



}
