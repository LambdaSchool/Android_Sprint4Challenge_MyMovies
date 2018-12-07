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

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public FavoriteMovie(String title, String year, int favorite) {
        this.title = title;
        this.year = Integer.parseInt(year.substring(0, 4));
        this.favorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getFavorite() {
        return favorite;
    }

    public String getStringYear() {
        String intAsString = Integer.toString(year);
        return intAsString;
    }


    @Override
    public String toString() {
        return title + " (" + year + ")" ;
    }
}
