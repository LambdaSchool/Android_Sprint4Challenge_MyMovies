package com.lambdaschool.sprint4challenge_mymovies.sqldb;

import android.provider.BaseColumns;

public class FavoriteMovieDbContract {

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + FavoriteMovieEntry.TABLE_NAME + " (\n" +
                    FavoriteMovieEntry._ID + " INTEGER PRIMARY KEY,\n" +
                    FavoriteMovieEntry.COLUMN_NAME_TITLE + " TEXT,\n" +
                    FavoriteMovieEntry.COLUMN_NAME_IMAGE_URL + " TEXT,\n" +
                    FavoriteMovieEntry.COLUMN_NAME_IS_WATCHED + " INTEGER);";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + FavoriteMovieEntry.TABLE_NAME + ";";

    public class FavoriteMovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "FavoriteMovies";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";
        public static final String COLUMN_NAME_IS_WATCHED = "is_watched";
    }
}
