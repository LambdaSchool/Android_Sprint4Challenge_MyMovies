package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MoviesRepository {
    private ArrayList<MovieOverview> data;

    public MoviesRepository() {
        data = new ArrayList<>();
    }

    public ArrayList<MovieOverview> UpdateData(ArrayList<MovieOverview> updatedData){
        data.clear();
        data.addAll(updatedData);
        return data;
    }

    public MutableLiveData<ArrayList<MovieOverview>> getData(){
        MutableLiveData<ArrayList<MovieOverview>> liveData = new MutableLiveData<>();
        liveData.setValue(data);
        return liveData;
    }
}
