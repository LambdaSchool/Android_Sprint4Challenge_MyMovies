package com.lambdaschool.sprint4challenge_mymovies.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lambdaschool.sprint4challenge_mymovies.Movie;

public class FavoriteMovieSQLDAO {
    private SQLiteDatabase db;

    public FavoriteMovieSQLDAO(Context context) {
        FavoriteMovieSQLHelper dbHelper=new FavoriteMovieSQLHelper( context );
        this.db = dbHelper.getWritableDatabase();
    }
    public FavoriteMovieSQLDAO(SQLiteDatabase db) {
        this.db = db;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public void add(Movie movie){
        ContentValues values = getContentValues(movie);
        final long insert = db.insert(FavoriteMovieSQLContract.MovieFavorite.TABLE_NAME, null, values);

    }

    public void delete(Movie movie){
        int affectedRows = db.delete(FavoriteMovieSQLContract.MovieFavorite.TABLE_NAME,
                FavoriteMovieSQLContract.MovieFavorite._ID + "=?",
                new String[]{Integer.toString(movie.getiID())});
    }

    private ContentValues getContentValues(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(FavoriteMovieSQLContract.MovieFavorite._ID, movie.getiID());
        values.put(FavoriteMovieSQLContract.MovieFavorite.COLUMN_NAME_TITLE, movie.getMovieOverview().getTitle());
        if(movie.getMovieOverview().getRelease_date().equals( "" )){
            values.put(FavoriteMovieSQLContract.MovieFavorite.COLUMN_NAME_YEAR, 0);

        }else{

            values.put(FavoriteMovieSQLContract.MovieFavorite.COLUMN_NAME_YEAR, Integer.parseInt( movie.getMovieOverview().getRelease_date().substring( 0,4 )));
        }
        values.put(FavoriteMovieSQLContract.MovieFavorite.COLUMN_NAME_WATCHED, movie.isbWatched());
        return values;
    }
}
