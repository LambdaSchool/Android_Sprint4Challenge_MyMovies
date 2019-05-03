package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.ArrayList;

public class FavoriteMovieVM extends ViewModel {
    private MutableLiveData<ArrayList<FavoriteMovie>> favoriteMovies;
    private MutableLiveData<ArrayList<Integer>> favoriteIds;
    private FavoriteMovieRepo repo;

    public MutableLiveData<ArrayList<FavoriteMovie>> getFavoriteMovies(Context context) {
        if (favoriteMovies == null) {
            loadData(context);
        }
        return favoriteMovies;
    }

    public MutableLiveData<ArrayList<Integer>> getFavoriteIds() {
        return favoriteIds;
    }


    public void loadData(Context context) {
        repo = new FavoriteMovieRepo(context);
        favoriteMovies = repo.getFavoriteMovies();
        favoriteIds = repo.getFavoriteIds();
    }



    public FavoriteMovie updateMovie(int index, FavoriteMovie movie) {
        favoriteMovies.getValue().set(index, movie);
        repo.updateMovie(index, movie);
        return favoriteMovies.getValue().get(index);
    }

    public void addMovie(FavoriteMovie movie) {
        if (!favoriteIds.getValue().contains(movie.getId())) {
            favoriteMovies.getValue().add(movie);
            favoriteIds.getValue().add(movie.getId());
            repo.addMovie(movie);
        }
    }

    public void deleteMovie(int id) {
        if (favoriteIds.getValue().contains(id)) {
            int index = favoriteIds.getValue().indexOf(id);
            favoriteMovies.getValue().remove(index);
            favoriteIds.getValue().remove(index);
            repo.deleteMovie(id);
        }
    }

    public void deleteAll() {
        repo.deleteAll();
        favoriteMovies.getValue().clear();
        favoriteIds.getValue().clear();
    }


}
