package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDbHelper  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1111;
    private static final String DATABASE_NAME = "favorite_movies.db";

    public MovieDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Standard Implemented Methods
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieDbContract.MovieEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MovieDbContract.MovieEntry.SQL_DELETE_TABLE);
        this.onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onUpgrade(db, oldVersion, newVersion);
    }
}
