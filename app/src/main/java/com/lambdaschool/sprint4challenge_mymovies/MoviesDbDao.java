package com.lambdaschool.sprint4challenge_mymovies;

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
}
