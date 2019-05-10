package com.lambdaschool.sprint4challenge_mymovies;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;


public class Movie  {
    public int getiID() {
        return iID;
    }

    public void setiID(int iID) {
        this.iID = iID;
    }

    public MovieOverview getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(MovieOverview movieOverview) {
        this.movieOverview = movieOverview;
    }

    public boolean isbWatched() {
        return bWatched;
    }

    public void setbWatched(boolean bWatched) {
        this.bWatched = bWatched;
    }


    private int iID;
    private MovieOverview movieOverview;
    private boolean bWatched;

    public Movie(int iID, MovieOverview movieOverview, boolean bWatched) {
        this.iID = iID;
        this.movieOverview = movieOverview;
        this.bWatched = bWatched;
    }

    public Movie(MovieOverview movieOverview, boolean bWatched) {
        this.iID = (int)System.currentTimeMillis();
        this.movieOverview = movieOverview;
        this.bWatched = bWatched;
    }



}