package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritesViewActivity extends AppCompatActivity implements View.OnClickListener {

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

        for (FavoriteMovie favoriteMovie : favoriteMovies) {
            TextView textView = new TextView(context);
            textView.setText(favoriteMovie.getName());
            textView.setOnClickListener(this);
            if (!favoriteMovie.isWatched()) {
                textView.setTypeface(Typeface.DEFAULT_BOLD);
            }
            favoritesLinearLayout.addView(textView);

        }
    }

    @Override
    public void onClick(View v) {

    }
}
