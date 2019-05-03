package com.lambdaschool.sprint4challenge_mymovies.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;
import com.lambdaschool.sprint4challenge_mymovies.repository.FavoriteMovieRepository;

import java.util.ArrayList;

public class FavoriteMoviesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<FavoriteMovie>> liveData;
    private FavoriteMovieRepository repository;

    public LiveData<ArrayList<FavoriteMovie>> getData() {
        if (liveData == null) {
            loadData();
        }

        return liveData;
    }

    private void loadData() {
        repository = new FavoriteMovieRepository();
        liveData = repository.getData();
    }

    public void addFavoriteMovie(FavoriteMovie favoriteMovie) {
        liveData.setValue(repository.addFavoriteMovie(favoriteMovie));
    }

    public void removeFavoriteMovie(long id) {
        liveData.setValue(repository.removeFavoriteMovie(id));
    }

    public void setFavoriteMovieIsWatched(long id, boolean isWatched) {
        repository.setFavoriteMovieIsWatched(id, isWatched);
    }

    public ArrayList<FavoriteMovie> refresh() {
        liveData.setValue(repository.refresh());

        return liveData.getValue();
    }

}
