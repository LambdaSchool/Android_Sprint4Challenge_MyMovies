package com.lambdaschool.sprint4challenge_mymovies.repository;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.sprint4challenge_mymovies.dao.FavoriteMovieDao;
import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;

import java.util.ArrayList;

public class FavoriteMovieRepository {

    private FavoriteMovieDao favoriteMovieDao;
    private ArrayList<FavoriteMovie> favoriteMoviesCache;

    public FavoriteMovieRepository() {
        favoriteMovieDao = new FavoriteMovieDao();
    }

    public MutableLiveData<ArrayList<FavoriteMovie>> getData() {
        favoriteMoviesCache = favoriteMovieDao.getFavoriteMovies();
        MutableLiveData<ArrayList<FavoriteMovie>> liveData = new MutableLiveData<>();

        liveData.setValue(favoriteMoviesCache);
        return liveData;
    }

    public ArrayList<FavoriteMovie> addFavoriteMovie(FavoriteMovie favoriteMovie) {
        favoriteMovieDao.addFavoriteMovie(favoriteMovie);
        favoriteMoviesCache.add(favoriteMovie);

        return favoriteMoviesCache;
    }

}
