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

}
