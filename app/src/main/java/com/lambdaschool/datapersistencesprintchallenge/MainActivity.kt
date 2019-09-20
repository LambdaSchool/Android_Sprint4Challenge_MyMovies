package com.lambdaschool.datapersistencesprintchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack.listOfMovies
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MovieRetroFitObject
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MovieRetroFitObject.getListOfMovies("batman").enqueue(ListOfMoviesCallBack)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = DisplayMovieList(listOfMovies)

        button_favorite_movies.setOnClickListener {
            startActivity(Intent())
        }
    }
}
