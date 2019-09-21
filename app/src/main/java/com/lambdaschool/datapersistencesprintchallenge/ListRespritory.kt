package com.lambdaschool.datapersistencesprintchallenge

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.IDS
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants.MOVIE_NAMES
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview

class ListRespritory{
    companion object{

        var createList = mutableListOf<MovieOverview>()


        fun createView(){
            for (index in 0 until MOVIE_NAMES.size)
                createList.add(MovieOverview(IDS[index], MOVIE_NAMES[index], isVaf = false))
        }
    }
}