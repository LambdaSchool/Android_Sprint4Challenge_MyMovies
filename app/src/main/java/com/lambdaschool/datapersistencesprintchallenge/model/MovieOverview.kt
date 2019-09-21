package com.lambdaschool.sprint4challenge_mymovies.model

import java.io.Serializable

class MovieSearchResult(val results: List<MovieOverview>)

class MovieOverview(
        val id: Int,
    //    val original_language: String,
    //    val original_title: String,
    //    val overview: String,
    //    val popularity: Float,
    //    val poster_path: String,
    //    val release_date: String,
        val title: String,
        var isVaf: Boolean
       // val isVideo: Boolean,
       // val vote_average: Float,
       // val vote_count: Int
):Serializable