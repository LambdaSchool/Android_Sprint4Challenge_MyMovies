package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MovieOverviewRepo {
    public static MutableLiveData<ArrayList<MovieOverview>> getOverviewList() {
        final MutableLiveData<ArrayList<MovieOverview>> liveDataList =new MutableLiveData<>();
        ArrayList<MovieOverview> overviews = MovieApiDao.searchMovies();
        liveDataList.setValue(overviews);
        return liveDataList;
}
