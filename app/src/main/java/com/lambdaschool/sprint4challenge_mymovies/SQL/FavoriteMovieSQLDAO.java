package com.lambdaschool.sprint4challenge_mymovies.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.MotionEventCompat;

import com.lambdaschool.sprint4challenge_mymovies.Movie;
import com.lambdaschool.sprint4challenge_mymovies.MoviesList;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovieSQLDAO {
    private SQLiteDatabase db;

    public FavoriteMovieSQLDAO(Context context) {
        FavoriteMovieSQLHelper dbHelper=new FavoriteMovieSQLHelper( context );
        db=dbHelper.getWritableDatabase();

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

    public MoviesList getAllSQL(){
        final Cursor cursor = db.rawQuery("SELECT * FROM " + FavoriteMovieSQLContract.MovieFavorite.TABLE_NAME,
                new String[]{});
        MoviesList ml=new MoviesList(  );

        while (cursor.moveToNext()) {
            ml.add(getMovieFromCursor( cursor));
        }
        cursor.close();
        return ml;
    }

    public void add(Movie movie){
        ContentValues values = getContentValues(movie);


        final long insert = db.insert(FavoriteMovieSQLContract.MovieFavorite.TABLE_NAME, null, values);

        System.out.printf( String.valueOf( insert ) );
        return;
    }

    public void delete(Movie movie){

        int affectedRows = db.delete(FavoriteMovieSQLContract.MovieFavorite.TABLE_NAME,
                FavoriteMovieSQLContract.MovieFavorite._ID + "=?",
                new String[]{Integer.toString(movie.getiID())});

        System.out.printf( String.valueOf( affectedRows ) );
        return;
    }

    public void update(Movie movie){
        int affectedRows = db.update(
                FavoriteMovieSQLContract.MovieFavorite.TABLE_NAME,
                getContentValues(movie),
                FavoriteMovieSQLContract.MovieFavorite._ID + "=?",  //id=1
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

    private Movie getMovieFromCursor(Cursor cursor) {
        int index = cursor.getColumnIndexOrThrow( FavoriteMovieSQLContract.MovieFavorite._ID);
        int id    = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(FavoriteMovieSQLContract.MovieFavorite.COLUMN_NAME_TITLE);
        String strTitle = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(FavoriteMovieSQLContract.MovieFavorite.COLUMN_NAME_YEAR);
        int iYear = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(FavoriteMovieSQLContract.MovieFavorite.COLUMN_NAME_WATCHED);
        boolean bWatched;
        if (cursor.getInt( index )==0) bWatched = false;
        else bWatched = true;

        return new Movie( id, strTitle, iYear, bWatched );
    }
}
