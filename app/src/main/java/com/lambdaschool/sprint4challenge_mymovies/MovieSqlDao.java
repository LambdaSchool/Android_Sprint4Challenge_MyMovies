package com.lambdaschool.sprint4challenge_mymovies;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.NetworkAdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieSqlDao {
	
	static String bitmapString;
	
	private static SQLiteDatabase db;
	
	public static void addMovie(MovieOverview movie) {
		ContentValues values = getContentValues(movie);
		
		final long insert = db.insert(MovieDbContract.MovieEntry.TABLE_NAME, null, values);
	}
	
	public void updateMovie(MovieOverview movie) {
		int affectedRows = db.update(
				MovieDbContract.MovieEntry.TABLE_NAME,
				getContentValues(movie),
				MovieDbContract.MovieEntry._ID + "=?",
				new String[]{Integer.toString(movie.getId())});
	}
	
	public void deleteMovie(MovieOverview movie) {
		int affectedRows = db.delete(MovieDbContract.MovieEntry.TABLE_NAME,
				MovieDbContract.MovieEntry._ID + "=?",
				new String[]{Integer.toString(movie.getId())});
	}
	
	private static ContentValues getContentValues(MovieOverview movie) {
		ContentValues values = new ContentValues();
		
		loadBitmapString(movie.getPoster_path());
		
		values.put(MovieDbContract.MovieEntry._ID, movie.getId());
		values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE, movie.getTitle());
		values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_IS_WATCHED, movie.isWatched());
		values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_SUMMARY, movie.getOverview());
		values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_IMAGE, getBitmapString());
		values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_RATING, movie.getVote_average());
		values.put(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR, movie.getRelease_date());
		return values;
	}
	
	public static void setBitmapString(String bitmapStringSet){
		bitmapString = bitmapStringSet;
	}
	
	public static String getBitmapString(){
		return bitmapString;
	}
	
	
	public static void loadBitmapString(final String bitmapPath){
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Bitmap bitmap = NetworkAdapter.getBitmapFromURL(bitmapPath);
				
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] byteArray = stream.toByteArray();
				bitmap.recycle();
				
				String bitmapString = new String(byteArray);
				
				setBitmapString(bitmapString);
				
			}
		}).start();
	}
	
	public List<MovieOverview> getAllMovies() {
		final Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDbContract.MovieEntry.TABLE_NAME,
				new String[]{});
		
		List<MovieOverview> rows = new ArrayList<>();
		while (cursor.moveToNext()) {
			rows.add(getMovie(cursor));
		}
		
		cursor.close();
		return rows;
	}
	
	private MovieOverview getMovie(Cursor cursor) {
		int index;
		index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry._ID);
		int id = cursor.getInt(index);
		
		index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_TITLE);
		String title = cursor.getString(index);
		
		index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_IS_WATCHED);
		boolean isWatched;
		if(cursor.getInt(index) == 1){isWatched = true;} else{isWatched = false;}
		
		index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_RATING);
		int rating = cursor.getInt(index);
		
		index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_SUMMARY);
		String summary = cursor.getString(index);
		
		index = cursor.getColumnIndexOrThrow(MovieDbContract.MovieEntry.COLUMN_NAME_MOVIE_YEAR);
		int year = cursor.getInt(index);
		
		
		
		
		return new MovieOverview(id, title, year, summary, rating, isWatched);
	}

}