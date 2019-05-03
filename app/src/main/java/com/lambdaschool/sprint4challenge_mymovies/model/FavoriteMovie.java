package com.lambdaschool.sprint4challenge_mymovies.model;

public class FavoriteMovie {

    private long id;
    private String title;
    private String imageUrl;
    private boolean isWatched;

    public FavoriteMovie(long id, String title, String imageUrl, boolean isWatched) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.isWatched = isWatched;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }
}
