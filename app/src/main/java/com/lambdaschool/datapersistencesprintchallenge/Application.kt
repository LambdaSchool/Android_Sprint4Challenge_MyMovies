package com.lambdaschool.datapersistencesprintchallenge
import android.app.Application
import com.lambdaschool.datapersistencesprintchallenge.database.FavouriteMovieDBRepo


val repo: FavouriteMovieRepoInterface by lazy {

    App.repo!!

}

class App : Application() {

    companion object {
        var repo: FavouriteMovieRepoInterface? = null
    }
    override fun onCreate() {
        super.onCreate()
        repo = FavouriteMovieDBRepo(applicationContext)

    }
}