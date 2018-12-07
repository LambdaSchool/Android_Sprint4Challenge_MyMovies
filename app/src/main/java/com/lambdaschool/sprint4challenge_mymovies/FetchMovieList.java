package com.lambdaschool.sprint4challenge_mymovies;

import android.os.AsyncTask;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.NetworkAdapter;

import java.util.ArrayList;

public class FetchMovieList extends AsyncTask<String, Void, String> {

    MovieListResponse movieListener;

    @Override
    protected String doInBackground(String... strings) {

        String resp = NetworkAdapter.httpRequest(strings[0]);

        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        MovieApiDao parser = new MovieApiDao(s);
        ArrayList<MovieOverview> searchMovies = parser.searchMovies(MainActivity.querySearch);

//        MovieParser parser = new MovieParser(s);
        movieListener.onMovieNamesLoaded(searchMovies);

    }
}
