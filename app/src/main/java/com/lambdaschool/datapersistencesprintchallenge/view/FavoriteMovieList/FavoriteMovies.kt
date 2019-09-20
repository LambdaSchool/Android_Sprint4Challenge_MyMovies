package com.lambdaschool.datapersistencesprintchallenge.view.FavoriteMovieList

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack
import com.lambdaschool.datapersistencesprintchallenge.room.App
import com.lambdaschool.datapersistencesprintchallenge.room.DataBaseBuilder
import com.lambdaschool.datapersistencesprintchallenge.room.DataBaseBuilder.Companion.favoriteListOfMovies
import com.lambdaschool.datapersistencesprintchallenge.room.FavoriteMovie
import kotlinx.android.synthetic.main.activity_favorite_movies.*
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteMovies : AppCompatActivity(), DataBaseBuilder.UpdateRecycler{
    override fun update() {
        adapter?.notifyDataSetChanged()
    }


    var adapter: DisplayFavoriteMovieList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)

        adapter = DisplayFavoriteMovieList(favoriteListOfMovies)

        DataBaseBuilder.getAllFavoriteMovies(this)



        recycler_view_favorite.layoutManager = LinearLayoutManager(this)
        recycler_view_favorite.adapter = adapter



    }



}
