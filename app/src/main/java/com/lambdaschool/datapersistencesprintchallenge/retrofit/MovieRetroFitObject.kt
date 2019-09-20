package com.lambdaschool.datapersistencesprintchallenge.retrofit

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MovieRetroFitObject {
    val retrofit = Retrofit.Builder()
        .baseUrl(MovieConstants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    fun getListOfMovies(movieName: String): Call<MovieSearchResult>{
        return retrofit.create(MovieAPI::class.java).getListOfMovies(movieName)
    }
}