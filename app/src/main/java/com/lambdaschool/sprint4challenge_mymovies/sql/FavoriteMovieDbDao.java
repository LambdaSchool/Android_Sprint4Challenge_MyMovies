package com.lambdaschool.sprint4challenge_mymovies.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.lambdaschool.sprint4challenge_mymovies.FavoriteMovie;

public class FavoriteMovieDbDao {
    private static SQLiteDatabase db;

    public static void initializeInstance(Context context) {
        if (db == null) {
            FavoritesDbHelper helper = new FavoritesDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    public static void createFavoriteMovie(FavoriteMovie movie) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_TITLE, movie.getTitle());
            values.put(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_OVERVIEW, movie.getOverview());
            values.put(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_RELEASE_DATE, movie.getRelease_date());
            values.put(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_VOTE_AVERAGE, movie.getVote_average());
            values.put(FavoriteMoviesDbContract.FavoritesEntry._ID, movie.getId());

            long resultId = db.insert(FavoriteMoviesDbContract.FavoritesEntry.TABLE_NAME, null, values);

        }
    }

    public static FavoriteMovie readFavorites(String id) {

        int index;
        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                    FavoriteMoviesDbContract.FavoritesEntry.TABLE_NAME,
                    FavoriteMoviesDbContract.FavoritesEntry._ID,
                    id),
                    null);

            FavoriteMovie favoriteMovie = null;

            if (cursor.moveToNext()) {
                return getFavoriteMovieFromCursor(cursor);

            } else {
                favoriteMovie = null;
            }
            cursor.close();
            return favoriteMovie;

        } else {
            return null;
        }
    }

    public static void updateNote(FavoriteMovie movie) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'",
                    FavoriteMoviesDbContract.FavoritesEntry._ID,
                    movie.getId());

            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    FavoriteMoviesDbContract.FavoritesEntry.TABLE_NAME,
                    whereClause),
                    null);

            if(cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_TITLE, movie.getTitle());
                values.put(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_OVERVIEW, movie.getOverview());
                values.put(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_RELEASE_DATE, movie.getRelease_date());
                values.put(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_VOTE_AVERAGE, movie.getVote_average());

                final int affectedRows = db.update(FavoriteMoviesDbContract.FavoritesEntry.TABLE_NAME, values, whereClause, null);
            }
        }
    }

    public static void deleteNote(FavoriteMovie movie) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'",
                    FavoriteMoviesDbContract.FavoritesEntry._ID,
                    movie.getId());

            int affectedRows = db .delete(FavoriteMoviesDbContract.FavoritesEntry.TABLE_NAME, whereClause, null);
        }
    }

    @NonNull
    private static FavoriteMovie getFavoriteMovieFromCursor(Cursor cursor) {
        int index;
        FavoriteMovie favoriteMovie;
        index = cursor.getColumnIndexOrThrow(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_TITLE);
        String title = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_OVERVIEW);
        String overView = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_RELEASE_DATE);
        String releaseDate = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(FavoriteMoviesDbContract.FavoritesEntry.COLUMN_NAME_VOTE_AVERAGE);
        Double voteAverage = Double.valueOf(cursor.getString(index));

        index = cursor.getColumnIndexOrThrow(FavoriteMoviesDbContract.FavoritesEntry._ID);
        int id = cursor.getInt(index);

        favoriteMovie = new FavoriteMovie(title, overView, releaseDate, voteAverage, id);

        return favoriteMovie;
    }
}
