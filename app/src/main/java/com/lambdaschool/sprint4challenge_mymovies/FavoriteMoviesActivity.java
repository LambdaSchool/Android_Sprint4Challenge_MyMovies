package com.lambdaschool.sprint4challenge_mymovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class FavoriteMoviesActivity extends AppCompatActivity {
    private LinearLayout favoriteMoviesList;

    @Override
    //did nothing but grab handle in onCreate, most of the work is onResume.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        favoriteMoviesList = findViewById(R.id.movie_list);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<FavoriteMovie> favMovies = MovieDbDao.readFavoriteMovies();
        //making a textview for each movie in the saved favorites list (easier to manipulate that way imo, with single and double click listeners)
        for (int i = 0; i < favMovies.size(); i++) {
            final TextView tv = new TextView(this);
            final FavoriteMovie favMovie = favMovies.get(i);
            tv.setTextSize(20);
            tv.setText(favMovie.getTitle() + " (" + favMovie.getRelease_date() + ")");
            updateView(favMovie,tv);

            tv.setOnClickListener(new View.OnClickListener() {

                @Override
                //adding single click to strike through to show "viewed"
                public void onClick(View v) {
                    //changing "watched" status and updating the db and tv
                    if (favMovie.getWatched() == 0) {
                        favMovie.setWatched(1);
                        MovieDbDao.updateMovieWatched(favMovie);
                        updateView(favMovie,tv);
                    } else {
                        favMovie.setWatched(0);
                        MovieDbDao.updateMovieWatched(favMovie);
                        updateView(favMovie,tv);
                    }
                }
            });
            //adding long click to remove from favorites list.
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
        //method to set text color based on "watched" status
    private void updateView(FavoriteMovie favMovie, View tv) {
        if (favMovie.getWatched() == 0) {
            ((TextView)tv).setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            ((TextView)tv).setTextColor(getResources().getColor(android.R.color.holo_green_dark));

        }

    }
}


