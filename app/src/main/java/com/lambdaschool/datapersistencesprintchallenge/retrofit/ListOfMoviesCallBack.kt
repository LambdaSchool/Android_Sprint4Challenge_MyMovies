package com.lambdaschool.datapersistencesprintchallenge.retrofit

import com.lambdaschool.datapersistencesprintchallenge.room.FavoriteMovie
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ListOfMoviesCallBack : Callback<MovieSearchResult> {

    var listOfMovies = ArrayList<MovieOverview>()

    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
        println(t)
    }

    override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
        if (response.isSuccessful){
            response.body()?.results?.forEach {
                listOfMovies.add(it)
            }
        } else {
            println(response)
        }
    }
}