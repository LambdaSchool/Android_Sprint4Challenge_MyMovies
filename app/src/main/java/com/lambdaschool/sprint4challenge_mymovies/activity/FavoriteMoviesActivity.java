package com.lambdaschool.sprint4challenge_mymovies.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lambdaschool.sprint4challenge_mymovies.R;
import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;
import com.lambdaschool.sprint4challenge_mymovies.view_model.FavoriteMoviesViewModel;

import java.util.ArrayList;

public class FavoriteMoviesActivity extends AppCompatActivity {

    private FavoriteMoviesViewModel favoriteMoviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoriteMoviesViewModel = ViewModelProviders.of(this).get(FavoriteMoviesViewModel.class);

        favoriteMoviesViewModel.getData().observe(this, new Observer<ArrayList<FavoriteMovie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<FavoriteMovie> favoriteMovies) {

            }
        });

    }
}
