package com.lambdaschool.sprint4challenge_mymovies;

import org.json.JSONObject;

public class FavoriteMovie {

    private String title;
    private int year, favorite;

    public FavoriteMovie(String title, int year, int favorite) {
        this.title = title;
        this.year = year;
        this.favorite = favorite;
    }
}
