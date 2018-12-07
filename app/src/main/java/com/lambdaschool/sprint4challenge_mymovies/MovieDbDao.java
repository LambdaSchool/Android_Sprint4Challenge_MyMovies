package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieDbDao {

    private static SQLiteDatabase db;

    public static void initializeInstance(Context context) {

        if (db == null) {
            MovieDbHelper helper = new MovieDbHelper(context);
            db = helper.getWritableDatabase();
        }

    }

    //CRUD

    public static void createFavoriteMovie(FavoriteMovie movie) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE, movie.getTitle());
            values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR, movie.getYear());
            values.put(MovieDbContract.MovieEntry.COLUMN_NAME_FAVORITE, movie.getFavorite());
            db.insertOrThrow(MovieDbContract.MovieEntry.TABLE_NAME, null, values);
        }

    }

    public static ArrayList<FavoriteMovie> readFaveMovies(){
        ArrayList<FavoriteMovie> movies = new ArrayList<FavoriteMovie>();
        int i;
        String format = String.format("SELECT * FROM %s;", MovieDbContract.MovieEntry.TABLE_NAME);
        Cursor cursor = db.rawQuery(format, null);

        while (cursor.moveToNext()) {
            i = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE);
            String title = cursor.getString(i);
            i = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR);
            String year = cursor.getString(i);
            i = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_FAVORITE);
            int favorite = cursor.getInt(i);
            FavoriteMovie movie = new FavoriteMovie(title, year, favorite);
            movies.add(movie);
        }
    }

    public static void deleteMovie(String title) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'",
                    MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE,
                    title);
            int affectedRows = db.delete(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE,
                    title, null);

        }
    }


}
