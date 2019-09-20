package com.lambdaschool.datapersistencesprintchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<MovieOverview> {

    val movieList = mutableListOf<MovieOverview>()
    lateinit var movieSearch: MovieAPI

    override fun onFailure(call: Call<MovieOverview>, t: Throwable) {

    }

    override fun onResponse(call: Call<MovieOverview>, response: Response<MovieOverview>) {
        if (response.isSuccessful){
            val movie = response.body() as MovieOverview

            val serMovie = MovieOverview(movie.id, movie.title)

            movieList.add(serMovie)


        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
