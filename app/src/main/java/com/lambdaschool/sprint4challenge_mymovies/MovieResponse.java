package com.lambdaschool.sprint4challenge_mymovies;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.List;

interface MovieResponse {

    void onMoviesLoaded(List<MovieOverview> movieOverviewList);

}
