package com.lambdaschool.sprint4challenge_mymovies.repository;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.sprint4challenge_mymovies.dao.FavoriteMovieDao;
import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;

import java.util.ArrayList;

public class FavoriteMovieRepository {

    private FavoriteMovieDao favoriteMovieDao;
    private ArrayList<FavoriteMovie> favoriteMovies;

    public FavoriteMovieRepository() {
        favoriteMovieDao = new FavoriteMovieDao();
    }

    public MutableLiveData<ArrayList<FavoriteMovie>> getData() {
        favoriteMovies = favoriteMovieDao.getFavoriteMovies();
        MutableLiveData<ArrayList<FavoriteMovie>> liveData = new MutableLiveData<>();

        liveData.setValue(favoriteMovies);
        return liveData;
    }

}
