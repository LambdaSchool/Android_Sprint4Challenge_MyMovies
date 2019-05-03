package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritesViewActivity extends AppCompatActivity {

    ArrayList<FavoriteMovie> favoriteMovies;
    Context                  context;
    LinearLayout             favoritesLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_view);
        context = this;
        favoritesLinearLayout = findViewById(R.id.favorites_linear_layout_child);

        favoriteMovies = MovieDbDao.getFavoriteMovies();

        for (final FavoriteMovie favoriteMovie : favoriteMovies) {
            final TextView textView = new TextView(context);
            textView.setText(favoriteMovie.getName());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favoriteMovie.setWatched(!favoriteMovie.isWatched());
                    MovieDbDao.editFavoriteMovie(favoriteMovie);
                    if (!favoriteMovie.isWatched()) {
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                    } else {
                        textView.setTypeface(Typeface.DEFAULT);
                    }
                }
            });
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    MovieDbDao.deleteFavoriteMovie(favoriteMovie);
                    favoritesLinearLayout.removeView(textView);
                    return true;
                }
            });
            if (!favoriteMovie.isWatched()) {
                textView.setTypeface(Typeface.DEFAULT_BOLD);
            }
            favoritesLinearLayout.addView(textView);

        }
    }


}
