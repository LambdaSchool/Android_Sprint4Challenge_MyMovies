package com.lambdaschool.datapersistencesprintchallenge.view.MovieList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.view.FavoriteMovieList.FavoriteMovies
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack.listOfMovies
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MovieRetroFitObject
import com.lambdaschool.datapersistencesprintchallenge.room.DataBaseBuilder
import kotlinx.android.synthetic.main.activity_main.*

class Movies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter =
            DisplayMovieList(
                listOfMovies
            )

        var test = listOfMovies

        search_bar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(movieName: String?): Boolean {
                MovieRetroFitObject.getListOfMovies(movieName!!).enqueue(ListOfMoviesCallBack)
                recycler_view.adapter?.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                listOfMovies.clear()
                recycler_view.adapter?.notifyDataSetChanged()
                return true
            }


        })



        button_favorite_movies.setOnClickListener {
            DataBaseBuilder.getAllFavoriteMovies()
            startActivity(Intent(this, FavoriteMovies::class.java))
        }
    }
}
