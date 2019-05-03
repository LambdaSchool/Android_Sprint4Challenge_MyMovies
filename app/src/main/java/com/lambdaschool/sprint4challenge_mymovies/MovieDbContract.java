package com.lambdaschool.sprint4challenge_mymovies;

import android.provider.BaseColumns;

public class MovieDbContract implements BaseColumns {


    public static final String TABLE_NAME          = "movies";
    public static final String COLUMN_NAME_NAME    = "name";
    public static final String COLUMN_NAME_WATCHED = "watched";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME_NAME + " TEXT," +
            COLUMN_NAME_WATCHED + " INTEGER" +
            " );";

    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


}
