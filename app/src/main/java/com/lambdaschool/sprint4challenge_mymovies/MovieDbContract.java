package com.lambdaschool.sprint4challenge_mymovies;

import android.provider.BaseColumns;

public class MovieDbContract implements BaseColumns {


    public static final String TABLE_NAME            = "movies";
    public static final String COLUMN_NAME_LAST_READ = "last_read";
    public static final String COLUMN_NAME_FAVORTIE  = "favorite";


    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " ( " +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_LAST_READ + " INTEGER," +
            COLUMN_NAME_FAVORTIE + " INTEGER" +
            " );";

    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


}
