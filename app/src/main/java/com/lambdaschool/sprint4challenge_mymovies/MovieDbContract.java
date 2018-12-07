package com.lambdaschool.sprint4challenge_mymovies;

import android.provider.BaseColumns;

public class MovieDbContract implements BaseColumns {

    public static final String TABLE_NAME = "movies";

    public static final String COLUMN_NAME_MOVIE_TITLE = "title";
    public static final String COLUMN_NAME_MOVIE_YEAR = "year";
    public static final String COLUMN_NAME_FAVORITE = "favorite";

    public static final String SQL_CREATE_TABLE = "CREAT TABLE " + TABLE_NAME +
            " ( " +
            _ID + "INTEGER PRIMARY KEY," +
            COLUMN_NAME_MOVIE_TITLE + " TEXT, " +
            COLUMN_NAME_MOVIE_YEAR + " INTEGER, " +
            COLUMN_NAME_FAVORITE + " INTEGER);";

    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    
}
