package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

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



}
