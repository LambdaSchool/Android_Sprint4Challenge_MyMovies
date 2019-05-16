package com.lambdaschool.sprint4challenge_mymovies;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;


public class Movie  {
    private int iID;
    private MovieOverview movieOverview;
    private boolean bFavorite,bWatched;

    public Movie(String strTitle,int iYear){
        iID=(int)System.currentTimeMillis();
        movieOverview=new MovieOverview(strTitle,  Integer.toString( iYear));

        bFavorite=true;
        bWatched=false;
    }
    public Movie(int id,String strTitle,int iYear,String strPosterPath, boolean bWatched){
        iID=id;
        movieOverview=new MovieOverview(strTitle,  Integer.toString( iYear),strPosterPath);

        bFavorite=true;
        this.bWatched=bWatched;
    }

    public Movie(int id,String strTitle,int iYear,boolean bWatched){
        iID=id;
        movieOverview=new MovieOverview(strTitle,  Integer.toString( iYear));

        bFavorite=true;
        this.bWatched=bWatched;
    }
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




    public boolean isbFavorite() {
        return bFavorite;
    }

    public void setbFavorite(boolean bFavorite) {
        this.bFavorite = bFavorite;
    }


    public Movie(int iID, MovieOverview movieOverview, boolean bWatched) {
        this.iID = iID;
        this.movieOverview = movieOverview;
        this.bWatched = bWatched;
    }

    public Movie(MovieOverview movieOverview, boolean bWatched) {
        this.iID = movieOverview.getId();
        this.movieOverview = movieOverview;
        this.bWatched = bWatched;
    }



}