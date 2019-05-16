package com.lambdaschool.sprint4challenge_mymovies.SQL;

import android.provider.BaseColumns;

public class FavoriteMovieSQLContract {
    public static class MovieFavorite implements BaseColumns{
        public static final String  TABLE_NAME        = "movie",
                                    COLUMN_NAME_TITLE = "title",
                                    COLUMN_NAME_YEAR = "year",
                                    COLUMN_NAME_POSTERPATH="imagepath",
                                    COLUMN_NAME_WATCHED = "Watched";


        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + " ( " +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_TITLE + " TEXT," +
                COLUMN_NAME_YEAR + " INTEGER," +
                COLUMN_NAME_POSTERPATH + " TEXT," +
                COLUMN_NAME_WATCHED + " BOOLEAN" +

                "  );";
        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

}
