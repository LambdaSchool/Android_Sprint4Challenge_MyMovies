package com.lambdaschool.datapersistencesprintchallenge.view.FavoriteMovieList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack
import com.lambdaschool.datapersistencesprintchallenge.room.DataBaseBuilder
import com.lambdaschool.datapersistencesprintchallenge.room.DataBaseBuilder.Companion.favoriteListOfMovies
import kotlinx.android.synthetic.main.activity_favorite_movies.*

class FavoriteMovies : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)

        recycler_view_favorite.layoutManager = LinearLayoutManager(this)
        val test = favoriteListOfMovies
        val i = 0
        recycler_view_favorite.adapter = DisplayFavoriteMovieList(favoriteListOfMovies)
    }
}
