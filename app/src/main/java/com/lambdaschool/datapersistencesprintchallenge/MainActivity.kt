package com.lambdaschool.datapersistencesprintchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MovieRetroFitObject
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MovieRetroFitObject.getListOfMovies("batman").enqueue(ListOfMoviesCallBack)

    }
}
