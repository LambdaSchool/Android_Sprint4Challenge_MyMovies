package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MoviesSqlDbDao {
    private static SQLiteDatabase db;

    static void initializeInstance(Context context) {
        if (db == null) {
            MoviesSqlDbHelper helper = new MoviesSqlDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    static void createMovie(FavoriteMovie favoriteMovie) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(MoviesSqlDbContract.MovieEntry._ID, favoriteMovie.getId());
            values.put(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_TITLE, favoriteMovie.getTitle());
            values.put(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_RELEASE_DATE, favoriteMovie.getRelease_date());
            values.put(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_WATCHED, 0);
            db.insert(MoviesSqlDbContract.MovieEntry.TABLE_NAME, null, values);
        }
    }


    static FavoriteMovie readMovie(int id) {
//        SELECT * FROM comics WHERE comic_id=200;
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
                    MoviesSqlDbContract.MovieEntry.TABLE_NAME,
                    MoviesSqlDbContract.MovieEntry._ID,
                    id),
                    null);
            FavoriteMovie favoriteMovie;
            int index;
            if (cursor.moveToNext() && (cursor.getCount() == 1)) {

                index = cursor.getColumnIndexOrThrow(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_TITLE);
                String title = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_RELEASE_DATE);
                String release_date = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_WATCHED);
                int watched = cursor.getInt(index);

                favoriteMovie = new FavoriteMovie(id, title, release_date, watched);
            } else {
                favoriteMovie = null;
            }
            cursor.close();
            return favoriteMovie;

        } else {
            return null;
        }
    }

    static void updateMovie(FavoriteMovie movie) {
        if (db != null) {
            String whereClause = String.format("%s = %s", MoviesSqlDbContract.MovieEntry._ID, movie.getId());
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    MoviesSqlDbContract.MovieEntry.TABLE_NAME,
                    whereClause),
                    null);
            if (cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_WATCHED, movie.getWatched());
                db.update(MoviesSqlDbContract.MovieEntry.TABLE_NAME, values, whereClause, null);
            }
            cursor.close();
        }
    }


    static void deleteMovie(int id) {
        if (db != null) {
            String whereClause = String.format("%s = '%s'",
                    MoviesSqlDbContract.MovieEntry._ID,
                    id);
            final Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                    MoviesSqlDbContract.MovieEntry.TABLE_NAME,
                    whereClause),
                    null);
            if (cursor.getCount() == 1) {
                db.delete(MoviesSqlDbContract.MovieEntry.TABLE_NAME, whereClause, null);
            }
            cursor.close();
        }
    }

    static ArrayList<FavoriteMovie> readFavorites() {
        ArrayList<FavoriteMovie> movies = new ArrayList<>();
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s",
                    MoviesSqlDbContract.MovieEntry.TABLE_NAME),null   );
            int index;
            while (cursor.moveToNext()) {
                index = cursor.getColumnIndexOrThrow(MoviesSqlDbContract.MovieEntry._ID);
                int id = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_TITLE);
                String title = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_RELEASE_DATE);
                String release_date = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(MoviesSqlDbContract.MovieEntry.COLUMN_NAME_WATCHED);
                int watched = cursor.getInt(index);

                movies.add(new FavoriteMovie(id,title,release_date, watched));
            }
            cursor.close();
        }
        return movies;
    }

    static ArrayList<Integer> readFavoriteIds() {
        ArrayList<Integer> movieIds = new ArrayList<>();
        if (db != null) {
            Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s",
                    MoviesSqlDbContract.MovieEntry.TABLE_NAME),null   );
            int index;
            while (cursor.moveToNext()) {
                index = cursor.getColumnIndexOrThrow(MoviesSqlDbContract.MovieEntry._ID);
                int id = cursor.getInt(index);
                movieIds.add(id);
            }
            cursor.close();
        }
        return movieIds;
    }

}
