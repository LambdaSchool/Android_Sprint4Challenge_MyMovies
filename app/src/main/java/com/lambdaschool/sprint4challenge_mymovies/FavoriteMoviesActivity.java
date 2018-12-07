package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoriteMoviesActivity extends AppCompatActivity {

    private Context context;
    private LinearLayout favesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);


        favesList = findViewById(R.id.fave_linear_layout);


        ArrayList<FavoriteMovie> chosenMovies = new ArrayList<>();
        chosenMovies = MovieDbDao.readFaveMovies();
        for (FavoriteMovie temp : chosenMovies) {
            final FavoriteMovie movie = temp;
            final TextView tv = new TextView(this);
            tv.setText(movie.toString());
            tv.setTextSize(22);
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MovieDbDao.deleteMovie(movie.getTitle());
                    tv.setVisibility(View.GONE);
                    return false;
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
