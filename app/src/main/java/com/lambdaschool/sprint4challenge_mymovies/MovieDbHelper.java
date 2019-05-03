package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final int version = 1;

    public MovieDbHelper(Context context) {
        super (context, MovieDbContract.TABLE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(MovieDbContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(MovieDbContract.SQL_DELETE_TABLE);
            onCreate(db);
        }
    }
}
