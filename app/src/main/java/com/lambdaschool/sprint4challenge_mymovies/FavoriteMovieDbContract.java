package com.lambdaschool.sprint4challenge_mymovies;

import android.provider.BaseColumns;

    public class FavoriteMovieDbContract {
        public static class FavoriteMovieEntry implements BaseColumns {
            public static final String TABLE_NAME = "favorite_movies";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_OVERVIEW = "overview";
            public static final String COLUMN_NAME_RELEASE_DATE = "release_date";
            public static final String COLUMN_NAME_WATCHED = "watched";

            public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                    +" ( "
                    + _ID + " INTEGER PRIMARY KEY, "
                    + COLUMN_NAME_TITLE + " TEXT, "
                    + COLUMN_NAME_OVERVIEW + " TEXT, "
                    + COLUMN_NAME_RELEASE_DATE + " TEXT, "
                    + COLUMN_NAME_WATCHED + " INTEGER);";

            public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "
                    + TABLE_NAME + ";";
        }
}
