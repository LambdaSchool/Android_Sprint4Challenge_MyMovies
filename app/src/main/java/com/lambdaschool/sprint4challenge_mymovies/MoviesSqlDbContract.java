package com.lambdaschool.sprint4challenge_mymovies;

import android.provider.BaseColumns;

public class MoviesSqlDbContract {
    static class MovieEntry implements BaseColumns {
        static final String TABLE_NAME = "movies";
        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_RELEASE_DATE = "release_date";
        static final String COLUMN_NAME_WATCHED = "watched";


        static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" +
                _ID + " INTEGER, " +
                COLUMN_NAME_TITLE + " STRING, " +
                COLUMN_NAME_RELEASE_DATE + " STRING, " +
                COLUMN_NAME_WATCHED + " INTEGER);";

        static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    }

}
