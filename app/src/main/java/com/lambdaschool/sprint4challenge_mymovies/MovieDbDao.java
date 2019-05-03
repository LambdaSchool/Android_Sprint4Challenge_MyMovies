package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MovieDbDao {
    private static SQLiteDatabase db;
    private static final String TAG = "MovieDbDao";

    public static void initializeInstance(Context context) {
        if (db == null) {
            MovieDbHelper movieDbHelper = new MovieDbHelper(context);
            db = movieDbHelper.getWritableDatabase();
        }
    }

    public static void addMovie(FavoriteMovie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieDbContract.COLUMN_NAME_NAME, movie.getName());
        contentValues.put(MovieDbContract.COLUMN_NAME_WATCHED, 0);

        long rowInserted = db.insert(MovieDbContract.TABLE_NAME, null, contentValues);

        if (rowInserted == -1) {
            Log.e(TAG, "Problem CREATING movie in Db.");
        }
    }

    public static ArrayList<FavoriteMovie> getFavoriteMovies(){
        ArrayList<FavoriteMovie> favoriteMovies = new ArrayList<>();
        String queryString = "SELECT * FROM " + MovieDbContract.TABLE_NAME + ";";

        Cursor cursor = db.rawQuery(queryString, null);
        while (cursor.moveToNext()) {

            int index = cursor.getColumnIndexOrThrow(MovieDbContract.COLUMN_NAME_NAME);
            FavoriteMovie favoriteMovie = new FavoriteMovie(cursor.getString(index));
            index = cursor.getColumnIndexOrThrow(MovieDbContract.COLUMN_NAME_WATCHED);
            favoriteMovie.setWatched(1==cursor.getInt(index)); //1== sets boolean to 1 if the int returned from the cursor is 1
            index = cursor.getColumnIndexOrThrow(MovieDbContract._ID);
            favoriteMovie.setId(cursor.getInt(index));
            favoriteMovies.add(favoriteMovie);
        }
        cursor.close();
        return favoriteMovies;
    }

    public static void editFavoriteMovie(FavoriteMovie favoriteMovie) {
        int isWatched = favoriteMovie.isWatched() ? 1 : 0; //sets up the integer for the Db
        String whereString = MovieDbContract._ID + " = " + favoriteMovie.getId() + ";";


        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieDbContract.COLUMN_NAME_WATCHED, isWatched);

        int rowsAffected = db.update(MovieDbContract.TABLE_NAME, contentValues, whereString, null);

        if (rowsAffected < 1) {
            Log.e(TAG, "Error updating this movie.");
        } else if (rowsAffected > 1) {
            Log.e(TAG, "Error: updated more than 1 movie.");

        }

    }

    public static void deleteFavoriteMovie(FavoriteMovie favoriteMovie) {
        int rowsAffected = db.delete(MovieDbContract.TABLE_NAME, MovieDbContract._ID + " = ?;", new String[]{String.valueOf(favoriteMovie.getId())});

        if (rowsAffected < 1) {
            Log.e(TAG, String.format("Movie #%d couldn't be DELETED.", favoriteMovie.getId()));
        }
    }



}
