package com.lambdaschool.sprint4challenge_mymovies;

import android.provider.BaseColumns;

public class MovieDbContract {
    public static class MovieEntry implements BaseColumns{
        public static final String TABLE_NAME = "movies";

        public static final String COLUMN_NAME_TITLE= "title";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " ( " +
                _ID + " TEXT PRIMARY KEY, " +
                COLUMN_NAME_TITLE + " TEXT); ";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }

}
