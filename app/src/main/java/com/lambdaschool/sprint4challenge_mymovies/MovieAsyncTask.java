package com.lambdaschool.sprint4challenge_mymovies;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.List;

class MovieAsyncTask extends AsyncTask<String, String, List<MovieOverview>> {

    MovieResponse movieResponseListener;

    @Override
    protected List<MovieOverview> doInBackground(String... strings) {

        String query = strings[0];
        Log.d("Query", query);          // check in logcat


        return MovieApiDao.searchMovies(query);
    }

    @Override
    protected void onPostExecute(List<MovieOverview> movieOverviews) {
        super.onPostExecute(movieOverviews);

        movieResponseListener.onMoviesLoaded(movieOverviews);
    }
}
