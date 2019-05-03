package com.lambdaschool.sprint4challenge_mymovies.dao;

import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;
import com.lambdaschool.sprint4challenge_mymovies.sqldb.FavoriteMovieDbHelper;

import java.util.ArrayList;

public class FavoriteMovieDao {

    public void addFavoriteMovie(FavoriteMovie favoriteMovie) {
        FavoriteMovieDbHelper.createFavoriteMovie(favoriteMovie);
    }

    public void removeFavoriteMovie(FavoriteMovie favoriteMovie) {
        FavoriteMovieDbHelper.deleteFavoriteMovie(favoriteMovie.getId());
    }

    public ArrayList<FavoriteMovie> getFavoriteMovies() {
        return FavoriteMovieDbHelper.readFavoriteMovies();
    }
}
