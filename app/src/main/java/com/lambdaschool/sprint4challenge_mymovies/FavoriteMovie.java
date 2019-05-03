package com.lambdaschool.sprint4challenge_mymovies;

public class FavoriteMovie {
    private String name;
    private boolean Watched;

    public FavoriteMovie(String name) {
        this.name = name;
        Watched = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWatched() {
        return Watched;
    }

    public void setWatched(boolean watched) {
        Watched = watched;
    }
}
