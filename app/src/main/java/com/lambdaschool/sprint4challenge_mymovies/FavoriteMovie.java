package com.lambdaschool.sprint4challenge_mymovies;

public class FavoriteMovie {

    private int      id;
    private String   title;
    private String   overview;
    private String releaseDate;
    private boolean watched;

    public FavoriteMovie(int id, String title, String overview, String releaseDate, boolean watched) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.watched = watched;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
