package com.lambdaschool.sprint4challenge_mymovies;

import android.provider.BaseColumns;

public class MoviesDbContract {

    public class FavoriteEntry implements BaseColumns{
        public static final String TABLE_NAME = "favorites";

        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_FAVORITE = "favorite";
        public static final String COLUMN_NAME_WATCHED = "watched";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME_TITLE + " TEXT, " + COLUMN_NAME_FAVORITE +
                " INTEGER, " + COLUMN_NAME_WATCHED + " INTEGER" + " );";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }
}
