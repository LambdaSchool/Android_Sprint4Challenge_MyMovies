package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MovieOverviewViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MovieOverview>> overviewList;

    public LiveData<ArrayList<MovieOverview>> getOverViewList() {
        if(overviewList == null) {
            loadList();
        }
        return overviewList;
    }

    private void loadList() {
        overviewList = MovieOverviewRepo.getOverviewList();
    }
}
