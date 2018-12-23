package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MovieDbDao{
    private static SQLiteDatabase sqLiteDatabase;

    static void initializeInstance(Context context){
        if (sqLiteDatabase == null) {
            MovieDbHelper helper = new MovieDbHelper(context);
            sqLiteDatabase = helper.getWritableDatabase();

        }
    }

    public static void createFavoriteMovie(FavoriteMovies favoriteMovies){
        if(sqLiteDatabase != null){
            ContentValues values = new ContentValues();
            values.put(MovieDbContract.MovieEntry.COLUMN_TITLE, favoriteMovies.getTitle());
            values.put(MovieDbContract.MovieEntry.COLUMN_RELEASE_DATE, favoriteMovies.getRelease_date());
            values.put(MovieDbContract.MovieEntry.COLUMN_WATCHED, favoriteMovies.isWatched());
            sqLiteDatabase.insertOrThrow(MovieDbContract.MovieEntry.TABLE_NAME, null, values);
        }
    }

    public static void deleteFavoriteMovie(FavoriteMovies favoriteMovies){
        if (sqLiteDatabase != null){
            String whereClause = String.format
                                 ("%s = '%s'", MovieDbContract.MovieEntry.COLUMN_TITLE, favoriteMovies.getTitle());
            final Cursor cursor = sqLiteDatabase.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                                  MovieDbContract.MovieEntry.TABLE_NAME, whereClause),
                    null);
            if (cursor.getCount() == 1) {
                sqLiteDatabase.delete(MovieDbContract.MovieEntry.TABLE_NAME, whereClause,
                     null);
            }
            cursor.close();
        }

    }

    public static void updateMovieWatched(FavoriteMovies favoriteMovies){
        if (sqLiteDatabase != null) {
            String whereClause = String.format
                                 ("%s = '%s'", MovieDbContract.MovieEntry.COLUMN_TITLE,
                                  favoriteMovies.getTitle());
            final Cursor cursor = sqLiteDatabase.rawQuery(String.format("SELECT * FROM %s WHERE %s",
                                  MovieDbContract.MovieEntry.TABLE_NAME, whereClause),
                    null);
            if (cursor.getCount() == 1) {
                ContentValues values = new ContentValues();
                values.put(MovieDbContract.MovieEntry.COLUMN_WATCHED, favoriteMovies.isWatched());
                sqLiteDatabase.update(MovieDbContract.MovieEntry.TABLE_NAME, values, whereClause,
                      null);
            }
            cursor.close();
        }
    }

    public static ArrayList<FavoriteMovies> listFavoriteMovies(){
        String getMovies = String.format("SELECT * FROM %s;", MovieDbContract.MovieEntry.TABLE_NAME);
        Cursor cursor = sqLiteDatabase.rawQuery(getMovies, null);
        int index;
        ArrayList<FavoriteMovies> favoriteMovies = new ArrayList<>();
        while(cursor.moveToNext()){
            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_TITLE);
            String title = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_RELEASE_DATE);
            String releaseDate = cursor.getString(index);
            index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_WATCHED);
            int watched = cursor.getInt(index);
            FavoriteMovies favoriteMovie = new FavoriteMovies(title, releaseDate, watched);
            favoriteMovies.add(favoriteMovie);
        }
        cursor.close();
        return favoriteMovies;
    }

}