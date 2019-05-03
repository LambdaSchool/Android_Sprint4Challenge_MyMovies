package com.lambdaschool.sprint4challenge_mymovies.sqldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;

import java.util.ArrayList;

public class FavoriteMovieDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FavoriteMovie.db";
    private static SQLiteDatabase sqLiteDatabase;

    public FavoriteMovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FavoriteMovieDbContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(FavoriteMovieDbContract.SQL_DELETE_TABLE);
        onCreate(db);
    }

    public static void init(Context context) {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = new FavoriteMovieDbHelper(context).getWritableDatabase();
        }
    }

    public static void createFavoriteMovie(FavoriteMovie favoriteMovie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoriteMovieDbContract.FavoriteMovieEntry._ID, favoriteMovie.getId());
        contentValues.put(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_TITLE, favoriteMovie.getTitle());
        contentValues.put(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_IMAGE_URL, favoriteMovie.getImageUrl());
        contentValues.put(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_IS_WATCHED, favoriteMovie.isWatched() ? 1 : 0);
        sqLiteDatabase.insert(FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME, null, contentValues);
    }

    public static void deleteFavoriteMovie(long id) {
        String whereClause = FavoriteMovieDbContract.FavoriteMovieEntry._ID + "==" + id;
        sqLiteDatabase.delete(FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME, whereClause, null);
    }

    public static ArrayList<FavoriteMovie> readFavoriteMovies() {
        String query = "SELECT * FROM " + FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        ArrayList<FavoriteMovie> favoriteMovies = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(FavoriteMovieDbContract.FavoriteMovieEntry._ID);
            long id = cursor.getLong(idIndex);
            int titleIndex = cursor.getColumnIndex(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_TITLE);
            String title = cursor.getString(titleIndex);
            int imageUrlIndex = cursor.getColumnIndex(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_IMAGE_URL);
            String imageUrl = cursor.getString(imageUrlIndex);
            int isWatchedIndex = cursor.getColumnIndex(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_IS_WATCHED);
            boolean isWatched = cursor.getInt(isWatchedIndex) == 1;

            favoriteMovies.add(new FavoriteMovie(id, title, imageUrl, isWatched));
        }

        cursor.close();

        return favoriteMovies;
    }

}
