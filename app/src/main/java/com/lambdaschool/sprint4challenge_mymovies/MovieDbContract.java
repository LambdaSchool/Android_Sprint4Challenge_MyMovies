package com.lambdaschool.sprint4challenge_mymovies;

import android.provider.BaseColumns;

public class MovieDbContract {
	public static class MovieEntry implements BaseColumns{
		public static final String TABLE_NAME = "movies";
		public static final String COLUMN_NAME_MOVIE_TITLE = "movie_title";
		public static final String COLUMN_NAME_MOVIE_RATING = "rating";
		//public static final String COLUMN_NAME_MOVIE_IMAGE = "image";
		public static final String COLUMN_NAME_MOVIE_SUMMARY = "summary";
		public static final String COLUMN_NAME_MOVIE_IS_WATCHED = "is_watched";
		public static final String COLUMN_NAME_MOVIE_YEAR = "year";
		
		public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
				"( " + _ID + " INTEGER PRIMARY KEY, " +
				COLUMN_NAME_MOVIE_TITLE + " TEXT," +
				COLUMN_NAME_MOVIE_RATING + " INTEGER," +
				COLUMN_NAME_MOVIE_SUMMARY + " TEXT, " +
				//COLUMN_NAME_MOVIE_IMAGE + " TEXT, " +
				COLUMN_NAME_MOVIE_YEAR + " INTEGER, " +
				COLUMN_NAME_MOVIE_IS_WATCHED + " INTEGER" +
				" );";
		
		public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	}

}
