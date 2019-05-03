package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class MovieDbDao {
    private static SQLiteDatabase db;

    public static void initializeInstance(Context context){
        if(db == null){
            MovieDbHelper helper = new MovieDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }
    public static void createFavoriteMovie(FavoriteMovie movie){
        if(db != null){
            ContentValues values = new ContentValues();
            values.put(MovieDbContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
            values.put(MovieDbContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getRelease_date());
            values.put(MovieDbContract.MovieEntry.COLUMN_WATCHED, movie.getWatched());
            db.insertOrThrow(MovieDbContract.MovieEntry.TABLE_NAME, null, values);
        }
    }
    public static void deleteMovie(String movieTitle) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'", MovieDbContract.MovieEntry.COLUMN_TITLE,
                    movieTitle);
            int affectedRows = db.delete(MovieDbContract.MovieEntry.TABLE_NAME, whereClause, null);
        }
    }
    public static void updateMovieWatched(FavoriteMovie movie){
        if (db != null) {
            String whereClause = String.format("%s = '%s'", MovieDbContract.MovieEntry.COLUMN_TITLE,
                    movie.getTitle());

            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    MovieDbContract.MovieEntry.TABLE_NAME, whereClause), null);

            if(cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(MovieDbContract.MovieEntry.COLUMN_WATCHED, movie.getWatched());
                final int affectedRows = db.update(MovieDbContract.MovieEntry.TABLE_NAME, values, whereClause, null);
            }
        }
    }
    public static ArrayList<FavoriteMovie> readFavoriteMovies(){
        String getMovies = String.format("SELECT * FROM %s;", MovieDbContract.MovieEntry.TABLE_NAME);
        Cursor cursor = db.rawQuery(getMovies, null);
        int index;
        ArrayList<FavoriteMovie> movies = new ArrayList<>();
        while(cursor.moveToNext()){
            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_TITLE);
            String title = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_RELEASE_DATE);
            String releaseDate = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_WATCHED);
            int watched = cursor.getInt(index);
            FavoriteMovie movie = new FavoriteMovie(title, releaseDate, watched);
            movies.add(movie);
        }
        cursor.close();
        return movies;
    }
}
