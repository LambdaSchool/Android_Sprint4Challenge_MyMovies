package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FavoriteMovieDbDao {
    private static SQLiteDatabase db;

    public static void initializeInstance(Context context) {
        FavoriteMovieDbHelper dbHelper = new FavoriteMovieDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static void addMovie(FavoriteMovie movie) {
        if (db != null) {
            ContentValues values = new ContentValues();

            values.put(FavoriteMovieDbContract.FavoriteMovieEntry._ID, movie.getId());
            values.put(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_TITLE, movie.getTitle());
            values.put(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_OVERVIEW, movie.getOverview());
            values.put(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_RELEASE_DATE, movie.getReleaseDate());
            values.put(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_WATCHED,
                    movie.isWatched() ? 1 : 0);

            db.insert(FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME, null, values);
        }
    }

    public static FavoriteMovie getMovie(int id) {
        String entryQuery = String.format("SELECT * FROM %s WHERE %s = %s",
                FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME,
                FavoriteMovieDbContract.FavoriteMovieEntry._ID, id);
        FavoriteMovie movie;
        int index;
        if (db != null) {
            Cursor cursor = db.rawQuery(entryQuery, null);
            if (cursor.moveToNext() && cursor.getCount() == 1) {
                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry._ID);
                int movieId = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_TITLE);
                String title = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_OVERVIEW);
                String overview= cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_RELEASE_DATE);
                String releaseDate = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_WATCHED);
                boolean watched = cursor.getInt(index) == 1;

                movie = new FavoriteMovie(movieId, title, overview, releaseDate, watched);
                cursor.close();
            } else {
                movie = null;
            }
            return movie;
        } else {
            return null;
        }
    }

    public static void updateMovie(FavoriteMovie movie) {
        if (db != null) {
            String whereClause = String.format("%s = %s", FavoriteMovieDbContract.FavoriteMovieEntry._ID,
                    movie.getId());
            String query = String.format("SELECT * FROM %s WHERE %s",
                    FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME, whereClause);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_WATCHED,
                        movie.isWatched() ? 1 : 0);

                db.update(FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME, values,
                        whereClause, null);
            }
            cursor.close();
        }
    }

    public static void deleteMovie (int id) {
        if (db != null) {
            String whereClause = String.format("%s = %s",
                    FavoriteMovieDbContract.FavoriteMovieEntry._ID, id);
            String query = String.format("SELECT * FROM %s WHERE %s",
                    FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME, whereClause);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() == 1) {
                db.delete(FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME, whereClause, null);
            }
            cursor.close();
        }
    }

    public static ArrayList<FavoriteMovie> readFavorites() {
        ArrayList<FavoriteMovie> movies = new ArrayList<>();
        if (db != null) {
            String query = String.format("SELECT * FROM %s",
                    FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME);
            Cursor cursor = db.rawQuery(query, null);
            int index;
            while (cursor.moveToNext()) {
                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry._ID);
                int movieId = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_TITLE);
                String title = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_OVERVIEW);
                String overview= cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_RELEASE_DATE);
                String releaseDate = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry.COLUMN_NAME_WATCHED);
                boolean watched = cursor.getInt(index) == 1;

                FavoriteMovie movie = new FavoriteMovie(movieId, title, overview, releaseDate, watched);
                movies.add(movie);
            }
            cursor.close();
        }
        return movies;
    }

    public static ArrayList<Integer> readFavoriteIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        if (db != null) {
            String query = String.format("SELECT * FROM %s",
                    FavoriteMovieDbContract.FavoriteMovieEntry.TABLE_NAME);
            Cursor cursor = db.rawQuery(query, null);
            int index;
            while (cursor.moveToNext()) {
                index = cursor.getColumnIndexOrThrow(FavoriteMovieDbContract.FavoriteMovieEntry._ID);
                int movieId = cursor.getInt(index);
                ids.add(movieId);
            }
            cursor.close();
        }
        return ids;
    }





}