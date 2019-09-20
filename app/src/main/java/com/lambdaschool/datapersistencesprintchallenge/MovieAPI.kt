package com.lambdaschool.datapersistencesprintchallenge

import com.google.gson.GsonBuilder
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.BASE_URL
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import okhttp3.OkHttpClient
import okhttp3.internal.tls.OkHostnameVerifier
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface MovieAPI{
    @GET("movie/{title-id}")
    fun getMovieById(@Path("title-id") nameOrOd: String): Call<MovieOverview>

    companion object{
    fun create():MovieAPI{
        val logger= HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC

        logger.level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder()
            .setLenient()
            .create()
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .retryOnConnectionFailure(false)
            .readTimeout(1000, TimeUnit.SECONDS)
            .connectTimeout(1500, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(MovieAPI::class.java)

    }
    }
}