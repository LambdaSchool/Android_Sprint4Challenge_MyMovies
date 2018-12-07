package com.lambdaschool.sprint4challenge_mymovies;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;

import java.util.ArrayList;

public class FavoriteMoviesActivity extends AppCompatActivity {
    private LinearLayout favoriteMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);

        favoriteMoviesList = findViewById(R.id.favorite_movies_list);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<FavoriteMovie> favMovies = MovieDbDao.readFavoriteMovies();
        for(int i = 0; i < favMovies.size(); i++){
            final TextView tv = new TextView(this);
            final FavoriteMovie favMovie = favMovies.get(i);
            tv.setTextSize(20);
            tv.setText(favMovie.getTitle() + " (" + favMovie.getRelease_date() + ")");
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("asdasd", favMovie.getTitle());
                    if(favMovie.getWatched() == 0){
                        favMovie.setWatched(1);
                        MovieDbDao.updateMovieWatched(favMovie);
                        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }else{
                        favMovie.setWatched(0);
                        MovieDbDao.updateMovieWatched(favMovie);
                        tv.setPaintFlags(tv.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MovieDbDao.deleteMovie(favMovie.getTitle());
                    tv.setVisibility(View.GONE);
                    return false;
                }
            });
            favoriteMoviesList.addView(tv);
        }
    }
}
