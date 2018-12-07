package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MoviesDbDao {

    private static SQLiteDatabase db;

    public static void initializeInstance(Context context) {

        if (db == null) {
            MovieDbHelper helper = new MovieDbHelper(context);
            db = helper.getWritableDatabase();
        }

    }

    public static void createFavoriteMovie(FavoriteMovie movie) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE, movie.getTitle());
            values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR, movie.getYear());
            values.put(MovieDbContract.MovieEntry.COLUMN_NAME_FAVORITE, movie.getFavorite());
            db.insertOrThrow(MovieDbContract.MovieEntry.TABLE_NAME, null, values);
        }

    }


}
