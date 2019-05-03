package com.lambdaschool.sprint4challenge_mymovies.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lambdaschool.sprint4challenge_mymovies.R;
import com.lambdaschool.sprint4challenge_mymovies.adapter.FavoriteMoviesAdapter;
import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;
import com.lambdaschool.sprint4challenge_mymovies.view_model.FavoriteMoviesViewModel;

import java.util.ArrayList;

public class FavoriteMoviesActivity extends AppCompatActivity {

    private FavoriteMoviesAdapter favoriteMoviesAdapter;
    private FavoriteMoviesViewModel favoriteMoviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);

        // setup recycler view
        RecyclerView favoriteMoviesRecyclerView = findViewById(R.id.activity_favorite_movies_recycler_favorite_movies);
        favoriteMoviesRecyclerView.setHasFixedSize(true);

        favoriteMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        favoriteMoviesAdapter = new FavoriteMoviesAdapter();
        favoriteMoviesAdapter.setOnFavoriteMovieDeleteListener(new FavoriteMoviesAdapter.OnFavoriteMovieDeleteListener() {
            @Override
            public void onDelete(FavoriteMovie favoriteMovie) {
                favoriteMoviesViewModel.removeFavoriteMovie(favoriteMovie.getId());
            }
        });
        favoriteMoviesAdapter.setOnFavoriteMovieIsWatchedChangedListener(new FavoriteMoviesAdapter.OnFavoriteMovieIsWatchedChangedListener() {
            @Override
            public void onWatchedChanged(FavoriteMovie favoriteMovie) {
                favoriteMoviesViewModel.setFavoriteMovieIsWatched(favoriteMovie.getId(), favoriteMovie.isWatched());
            }
        });
        favoriteMoviesRecyclerView.setAdapter(favoriteMoviesAdapter);

        // initialize favorite movies view model
        favoriteMoviesViewModel = ViewModelProviders.of(this).get(FavoriteMoviesViewModel.class);
        favoriteMoviesViewModel.getData().observe(this, new Observer<ArrayList<FavoriteMovie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<FavoriteMovie> favoriteMovies) {
                favoriteMoviesAdapter.setFavoriteMovies(favoriteMovies);
            }
        });

    }
}
