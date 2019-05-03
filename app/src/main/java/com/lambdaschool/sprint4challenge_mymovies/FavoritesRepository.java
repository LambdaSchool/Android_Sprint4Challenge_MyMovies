package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class FavoritesRepository {
    private ArrayList<FavoriteMovie> data;

    public FavoritesRepository() {
        data = new ArrayList<>();
    }

    public ArrayList<FavoriteMovie> UpdateData(ArrayList<FavoriteMovie> updatedData){
        data.clear();
        data.addAll(updatedData);
        return data;
    }

    public MutableLiveData<ArrayList<FavoriteMovie>> getData(){
        MutableLiveData<ArrayList<FavoriteMovie>> liveData = new MutableLiveData<>();
        liveData.setValue(data);
        return liveData;
    }
}