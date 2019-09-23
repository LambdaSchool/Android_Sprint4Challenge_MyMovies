package com.lambdaschool.datapersistencesprintchallenge.apiaccess

import android.app.Application
import com.lambdaschool.datapersistencesprintchallenge.model.*


val repo: FavoriteMovieRepoInterface by lazy {

    App.repo!!

}



class App : Application() {



    companion object {

        var repo: FavoriteMovieRepoInterface? = null

    }

    override fun onCreate() {

        super.onCreate()

        repo = FavouriteMovieDBRepo(applicationContext)



    }

}