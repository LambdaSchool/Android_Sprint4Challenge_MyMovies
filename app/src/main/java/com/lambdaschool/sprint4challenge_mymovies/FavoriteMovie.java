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

    public FavoriteMovie(String title) {
        this.title = title;
        this.favorite = false;
        this.watched = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFavorite(){
        if(favorite){
            return 1;
        }else{
            return 0;
        }
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

    public int getWatched(){
        if(watched){
            return 1;
        }else{
            return 0;
        }
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
