package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MoviesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MovieOverview>> data;
    private MoviesRepository repo;

    public LiveData<ArrayList<MovieOverview>> getData(){
        if(data == null){
            loadData();
        }
        return data;
    }

    private void loadData(){
        repo = new MoviesRepository();
        data = repo.getData();
    }

    public void updateData(ArrayList<MovieOverview> movieOverviews){
        data.setValue(repo.UpdateData(movieOverviews));
    }
}
