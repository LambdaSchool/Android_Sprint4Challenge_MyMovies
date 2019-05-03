package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class MovieDbSqlDao {
    private static SQLiteDatabase sqLiteDatabase;
    private static final String TAG = "MovieDbSqlDao";

    public static void initializeInstance(Context context) {
        if (sqLiteDatabase == null) {
            MovieDbHelper movieDbHelper = new MovieDbHelper(context);
            sqLiteDatabase = movieDbHelper.getWritableDatabase();
        }
    }

    public static void closeInstance() {
        sqLiteDatabase.close();
    }

    public static void addFavorite(FavoriteMovie favoriteMovie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieDbContract.MovieEntry._ID, favoriteMovie.getId());
        contentValues.put(MovieDbContract.MovieEntry.COLUMN_NAME_TITLE, favoriteMovie.getTitle());
        contentValues.put(MovieDbContract.MovieEntry.COLUMN_NAME_YEAR, favoriteMovie.getYear());
        contentValues.put(MovieDbContract.MovieEntry.COLUMN_NAME_IMAGE_SUFFIX, favoriteMovie.getImageSuffix());
        contentValues.put(MovieDbContract.MovieEntry.COLUMN_NAME_FAVORITE, favoriteMovie.isFavorite() ? 1 : 0);
        contentValues.put(MovieDbContract.MovieEntry.COLUMN_NAME_WATCHED, favoriteMovie.isWatched() ? 1 : 0);

        long rowInserted = sqLiteDatabase.insert(MovieDbContract.MovieEntry.TABLE_NAME, null, contentValues);

        if (rowInserted == -1) {
            Log.e(TAG, "Problem ADDING a favorite.");
        }
    }

    public static FavoriteMovie readFavorite(int movieId) {
        String queryString = "SELECT * FROM " + MovieDbContract.MovieEntry.TABLE_NAME + " WHERE " + MovieDbContract.MovieEntry._ID + " = " + movieId + ";";
        FavoriteMovie favoriteMovie = new FavoriteMovie();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if (cursor.moveToNext() && cursor.getCount() > 0) {

            int index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry._ID);
            favoriteMovie.setId(cursor.getInt(index));

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_TITLE);
            favoriteMovie.setTitle(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_YEAR);
            favoriteMovie.setYear(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_IMAGE_SUFFIX);
            favoriteMovie.setImageSuffix(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_FAVORITE);
            favoriteMovie.setFavorite(cursor.getInt(index) == 1);

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_WATCHED);
            favoriteMovie.setWatched(cursor.getInt(index) == 1);

        } else {
            Log.e(TAG, String.format("Cannot READ favorite #%d.", movieId));
            favoriteMovie = null;
        }
        cursor.close();

        return favoriteMovie;
    }

    public static void updateFavorite(FavoriteMovie favoriteMovie) {
        String whereString = MovieDbContract.MovieEntry._ID + " = " + favoriteMovie.getId() + ";";

        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieDbContract.MovieEntry.COLUMN_NAME_FAVORITE, favoriteMovie.isFavorite() ? 1 : 0);
        contentValues.put(MovieDbContract.MovieEntry.COLUMN_NAME_WATCHED, favoriteMovie.isWatched() ? 1 : 0);

        int rowsAffected = sqLiteDatabase.update(MovieDbContract.MovieEntry.TABLE_NAME, contentValues, whereString, null);

        if (rowsAffected < 1) {
            Log.e(TAG, "Error UPDATING the favorite.");
        }
    }

    public static void deleteFavorite(int movieId) {
        int rowsAffected = sqLiteDatabase.delete(MovieDbContract.MovieEntry.TABLE_NAME, MovieDbContract.MovieEntry._ID + " = ?;", new String[]{String.valueOf(movieId)});

        if (rowsAffected < 1) {
            Log.e(TAG, String.format("Favorite #%d couldn't be DELETED.", movieId));
        }
    }

    public static ArrayList<FavoriteMovie> readFavoriteMovies() {
        String queryString = "SELECT * FROM " + MovieDbContract.MovieEntry.TABLE_NAME + ";";
        ArrayList<FavoriteMovie> favoriteMovieArrayList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        while (cursor.moveToNext() && cursor.getCount() > 0) {
            FavoriteMovie favoriteMovie = new FavoriteMovie();

            int index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry._ID);
            favoriteMovie.setId(cursor.getInt(index));

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_TITLE);
            favoriteMovie.setTitle(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_YEAR);
            favoriteMovie.setYear(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_IMAGE_SUFFIX);
            favoriteMovie.setImageSuffix(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_FAVORITE);
            favoriteMovie.setFavorite(cursor.getInt(index) == 1);

            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_WATCHED);
            favoriteMovie.setWatched(cursor.getInt(index) == 1);

            favoriteMovieArrayList.add(favoriteMovie);
        }
/*        if (cursor.) {
            Log.e(TAG, "Cannot read any FAVORITES.");
        }*/
        cursor.close();

        return favoriteMovieArrayList;
    }
}
