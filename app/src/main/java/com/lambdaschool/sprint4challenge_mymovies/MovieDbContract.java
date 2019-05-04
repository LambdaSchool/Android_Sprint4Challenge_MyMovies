package com.lambdaschool.sprint4challenge_mymovies;

import android.provider.BaseColumns;

public class MovieDbContract implements BaseColumns {
    public static class MovieEntry implements BaseColumns{
        //Title of DB
        public final static String TABLE_NAME = "favorite_movies";
        //columns in the DB
        public final static String COLUMN_TITLE = "movie_title";
        public final static String COLUMN_RELEASE_DATE = "release_date";
        public final static String COLUMN_WATCHED = "watched";
        //Method to Create DB
        public final static String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_RELEASE_DATE + " TEXT, " +
                        COLUMN_WATCHED + " INTEGER);";
        //Method to Delete DB
        public final static String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }
}
