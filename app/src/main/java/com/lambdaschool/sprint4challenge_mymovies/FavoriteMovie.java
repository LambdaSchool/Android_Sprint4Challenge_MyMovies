package com.lambdaschool.sprint4challenge_mymovies;

public class FavoriteMovie {
    private String title;
    private boolean favorite;
    private boolean watched;

    public FavoriteMovie(String title, boolean favorite, boolean watched) {
        this.title = title;
        this.favorite = favorite;
        this.watched = watched;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
