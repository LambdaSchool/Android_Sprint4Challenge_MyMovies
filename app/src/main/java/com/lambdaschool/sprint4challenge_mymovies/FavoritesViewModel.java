package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import java.util.ArrayList;

public class FavoritesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<FavoriteMovie>> data;
    private FavoritesRepository repo;

    public LiveData<ArrayList<FavoriteMovie>> getData(){
        if(data == null){
            loadData();
        }
        return data;
    }

    private void loadData(){
        repo = new FavoritesRepository();
        data = repo.getData();
    }

    public void updateData(ArrayList<FavoriteMovie> movieOverviews){
        data.setValue(repo.UpdateData(movieOverviews));
    }
}