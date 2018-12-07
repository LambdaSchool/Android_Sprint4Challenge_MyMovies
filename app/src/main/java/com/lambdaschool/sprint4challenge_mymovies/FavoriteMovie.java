package com.lambdaschool.sprint4challenge_mymovies;

public class FavoriteMovie {
    private String title, release_date;

    public FavoriteMovie(String title, String release_date) {
        this.title = title;
        this.release_date = release_date;
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
}
