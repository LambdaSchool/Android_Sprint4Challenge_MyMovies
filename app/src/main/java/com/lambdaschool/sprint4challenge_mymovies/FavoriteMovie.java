package com.lambdaschool.sprint4challenge_mymovies;

public class FavoriteMovie {
    private String title, release_date;
    private int watched;

    public FavoriteMovie(String title, String release_date, int watched) {
        this.title = title;
        this.release_date = release_date;
        this.watched = watched;
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
