package com.lambdaschool.datapersistencesprintchallenge.view.FavoriteMovieList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack
import kotlinx.android.synthetic.main.activity_favorite_movies.*

class FavoriteMovies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)

        recycler_view_favorite.layoutManager = LinearLayoutManager(this)
        recycler_view_favorite.adapter =
            DisplayFavoriteMovieList(
                ListOfMoviesCallBack.favoriteListOfMovies
            )
    }
}
