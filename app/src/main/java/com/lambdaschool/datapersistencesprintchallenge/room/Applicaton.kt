package com.lambdaschool.datapersistencesprintchallenge.room

import android.app.Application

class App: Application(){

    companion object{
        var createDataBase: DataBaseBuilder? = null
    }

    override fun onCreate() {
        super.onCreate()
        createDataBase = DataBaseBuilder(applicationContext)

    }

}