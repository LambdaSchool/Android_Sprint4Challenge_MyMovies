package com.lambdaschool.sprint4challenge_mymovies;
import java.io.Serializable;

public class FavoriteMovie implements Serializable {
    private String title, release_date, overview;
    private int watched;

    public FavoriteMovie(String title, String release_date,int watched) {
        this.title = title;
        this.release_date = release_date;
        this.watched = watched;
    }
    public FavoriteMovie(String title, String release_date,String overview){
        this.title = title;
        this.release_date = release_date;
        this.overview = overview;
        this.watched = 0;
    }

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }

    public String getTitle() {
        return title;
    }


    public String getRelease_date() {
        return release_date;
    }


}
