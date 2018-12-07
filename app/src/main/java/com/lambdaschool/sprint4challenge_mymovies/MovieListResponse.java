package com.lambdaschool.sprint4challenge_mymovies;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;
import java.util.List;

public interface MovieListResponse {

    void onMovieNamesLoaded(ArrayList<MovieOverview> movieNames);

}
