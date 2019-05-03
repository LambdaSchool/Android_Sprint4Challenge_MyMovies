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

    public ArrayList<FavoriteMovie> removeFavoriteMovie(long id) {
        favoriteMovieDao.removeFavoriteMovie(id);
        for (int i = 0; i < favoriteMoviesCache.size(); ++i) {
            FavoriteMovie favoriteMovie = favoriteMoviesCache.get(i);
            if (favoriteMovie.getId() == id) {
                favoriteMoviesCache.remove(i);
                break;
            }
        }

        return favoriteMoviesCache;
    }

    public void setFavoriteMovieIsWatched(long id, boolean isWatched) {
        favoriteMovieDao.setFavoriteMovieIsWatched(id, isWatched);
        for (int i = 0; i < favoriteMoviesCache.size(); ++i) {
            FavoriteMovie favoriteMovie = favoriteMoviesCache.get(i);
            if (favoriteMovie.getId() == id) {
                favoriteMovie.setWatched(isWatched);
                break;
            }
        }
    }

    public ArrayList<FavoriteMovie> refresh() {
        favoriteMoviesCache = favoriteMovieDao.getFavoriteMovies();

        return favoriteMoviesCache;
    }

}
