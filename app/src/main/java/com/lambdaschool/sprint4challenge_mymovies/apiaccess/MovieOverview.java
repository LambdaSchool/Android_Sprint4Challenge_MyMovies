package com.lambdaschool.sprint4challenge_mymovies.apiaccess;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieOverview
{
    private double   vote_average;
    private String   backdrop_path;
    private boolean  adult;
    private int      id;
    private String   title;
    private String   overview;
    private String   original_language;
    private String   release_date;
    private String   original_title;
    private int      vote_count;
    private String   poster_path;
    private boolean  video;
    private double   popularity;
    public MovieOverview(String title,String release_date){
        this.title=title;
        this.release_date=release_date;
    }
    public MovieOverview(JSONObject json) {
        try {
            this.vote_average = json.getDouble("vote_average");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.backdrop_path = json.getString("backdrop_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.adult = json.getBoolean("adult");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.id = json.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.overview = json.getString("overview");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.original_language = json.getString("original_language");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.release_date = json.getString("release_date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.original_title = json.getString("original_title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.vote_count = json.getInt("vote_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.poster_path = json.getString("poster_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.video = json.getBoolean("video");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.popularity = json.getDouble("popularity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean isAdult() {
        return adult;
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

    public String getOriginal_language() {
        return original_language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public boolean isVideo() {
        return video;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
