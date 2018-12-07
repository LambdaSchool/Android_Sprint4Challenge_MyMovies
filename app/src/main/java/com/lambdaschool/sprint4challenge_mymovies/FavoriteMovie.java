package com.lambdaschool.sprint4challenge_mymovies;

public class FavoriteMovie {
    private int id, watched;
    private String title, release_date;


    FavoriteMovie(int id, String title, String release_date, int watched) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.watched = watched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getWatched() {
        return watched;
    }

    public void setWatched(int watched) {
        this.watched = watched;
    }
}
