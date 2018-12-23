package com.lambdaschool.sprint4challenge_mymovies;

public class FavoriteMovies {
    String title, release_date;
    int watched;


    public FavoriteMovies(String title, String release_date, int watched) {
        this.title = title;
        this.release_date = release_date;
        this.watched = watched;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int isWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }
}