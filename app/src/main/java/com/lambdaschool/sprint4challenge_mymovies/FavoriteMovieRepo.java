package com.lambdaschool.sprint4challenge_mymovies;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.ArrayList;

public class FavoriteMovieRepo {

    private ArrayList<FavoriteMovie> favoriteMovies;
    private ArrayList<Integer> favoriteIds;
    private Context context;

    public FavoriteMovieRepo(Context context) {
        favoriteMovies = new ArrayList<>();
        favoriteIds = new ArrayList<>();
        this.context = context;
        FavoriteMovieDbDao.initializeInstance(context);
    }

    public void addMovie(final FavoriteMovie movie) {
        FavoriteMovieDbDao.addMovie(movie);
    }

    public MutableLiveData<ArrayList<FavoriteMovie>> getFavoriteMovies() {
        final MutableLiveData<ArrayList<FavoriteMovie>> liveData = new MutableLiveData<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                favoriteMovies = FavoriteMovieDbDao.readFavorites();
                liveData.postValue(favoriteMovies);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        liveData.setValue(favoriteMovies);
        return liveData;
    }

    public MutableLiveData<ArrayList<Integer>> getFavoriteIds() {
        final MutableLiveData<ArrayList<Integer>> liveIdData = new MutableLiveData<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                favoriteIds = FavoriteMovieDbDao.readFavoriteIds();
                liveIdData.postValue(favoriteIds);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        liveIdData.setValue(favoriteIds);
        return liveIdData;
    }

    public void updateMovie(final int index, final FavoriteMovie movie) {
        FavoriteMovieDbDao.updateMovie(favoriteMovies.get(index));
    }

    public void deleteMovie(int id) {
        FavoriteMovieDbDao.deleteMovie(id);
    }

    public void deleteAll() {
        favoriteIds.clear();
        favoriteMovies.clear();
    }
}