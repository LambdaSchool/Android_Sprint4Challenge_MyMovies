package com.lambdaschool.datapersistencesprintchallenge.retrofit

import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieGetApi {

    // search/movie?query=Avengers&api_key=359211348348a13a2b996217f7538f45

    @GET("search/movie?query={movie-name}&api_key=359211348348a13a2b996217f7538f45")
    fun getListOfMovies(@Path("movie-name") movieName: String): Call<MovieSearchResult>


}