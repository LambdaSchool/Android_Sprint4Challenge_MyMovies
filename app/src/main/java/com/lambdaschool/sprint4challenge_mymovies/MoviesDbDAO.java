package com.lambdaschool.sprint4challenge_mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MoviesDbDAO {
    private static SQLiteDatabase db;

    public static void InitializeInstance(Context context){
        if(db == null){
            MoviesDbHelper helper = new MoviesDbHelper(context);
            db = helper.getWritableDatabase();
        }
    }

    public static void AddFavorite(FavoriteMovie movie){
        if(db != null){
            ContentValues values = new ContentValues();
            values.put(MoviesDbContract.FavoriteEntry.COLUMN_NAME_TITLE, movie.getTitle());
            values.put(MoviesDbContract.FavoriteEntry.COLUMN_NAME_FAVORITE, movie.getFavorite());
            values.put(MoviesDbContract.FavoriteEntry.COLUMN_NAME_WATCHED, movie.getWatched());

            long resultId = db.insert(MoviesDbContract.FavoriteEntry.TABLE_NAME, null, values);
        }
    }

    public static FavoriteMovie getFavoriteByTitle(String title){
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * FROM " + MoviesDbContract.FavoriteEntry.TABLE_NAME +  " WHERE " +
                    MoviesDbContract.FavoriteEntry.COLUMN_NAME_TITLE + " =?", new String[]{title});
            int index = 0;
            cursor.moveToFirst();
            try{
                index = cursor.getColumnIndexOrThrow(MoviesDbContract.FavoriteEntry.COLUMN_NAME_TITLE);
                String recievedTitle = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(MoviesDbContract.FavoriteEntry.COLUMN_NAME_WATCHED);
                int recievedWatched = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow(MoviesDbContract.FavoriteEntry.COLUMN_NAME_FAVORITE);
                int recievedFavorite = cursor.getInt(index);

                FavoriteMovie movie = new FavoriteMovie(recievedTitle, recievedFavorite, recievedWatched);
                return movie;
            }catch (CursorIndexOutOfBoundsException e){
                return null;
            }
        }else{
            return null;
        }
    }

    public static ArrayList<FavoriteMovie> getAllFavorites(){
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * FROM " + MoviesDbContract.FavoriteEntry.TABLE_NAME, null);
            ArrayList<FavoriteMovie> favoriteMovies = new ArrayList<>();
            int index = 0;

            while(cursor.moveToNext()){
                index = cursor.getColumnIndexOrThrow(MoviesDbContract.FavoriteEntry.COLUMN_NAME_TITLE);
                String title = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow(MoviesDbContract.FavoriteEntry.COLUMN_NAME_FAVORITE);
                int favorite = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow(MoviesDbContract.FavoriteEntry.COLUMN_NAME_WATCHED);
                int watched = cursor.getInt(index);

                FavoriteMovie movie = new FavoriteMovie(title, favorite, watched);
                favoriteMovies.add(movie);
            }
            return favoriteMovies;
        }else{
            return null;
        }
    }

    public static void updateFavorite(FavoriteMovie movie){
        if(db != null){
            ContentValues values = new ContentValues();
            values.put(MoviesDbContract.FavoriteEntry.COLUMN_NAME_WATCHED, movie.getWatched());
            values.put(MoviesDbContract.FavoriteEntry.COLUMN_NAME_FAVORITE, movie.getFavorite());
            values.put(MoviesDbContract.FavoriteEntry.COLUMN_NAME_TITLE, movie.getTitle());

            String whereClause = "WHERE " + MoviesDbContract.FavoriteEntry.COLUMN_NAME_TITLE + " =?";
            String[] whereArgs = new String[]{movie.getTitle()};

            db.update(MoviesDbContract.FavoriteEntry.TABLE_NAME, values, whereClause, whereArgs);
        }
    }

    public static void deleteFavorite(FavoriteMovie movie){
        if(db != null){
            String whereClause = " WHERE " + MoviesDbContract.FavoriteEntry.COLUMN_NAME_TITLE + " =?";
            String[] whereArgs = new String[]{movie.getTitle()};

            db.delete(MoviesDbContract.FavoriteEntry.TABLE_NAME, whereClause, whereArgs);
        }
    }
}
