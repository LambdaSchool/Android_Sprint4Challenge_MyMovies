package com.lambdaschool.sprint4challenge_mymovies.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lambdaschool.sprint4challenge_mymovies.R;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;
import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;
import com.lambdaschool.sprint4challenge_mymovies.view_model.FavoriteMoviesViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FavoriteMoviesViewModel favoriteMoviesViewModel;
    private SearchedMoviesAdapter searchedMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favoriteMoviesViewModel = ViewModelProviders.of(this).get(FavoriteMoviesViewModel.class);

        final EditText searchEditText = findViewById(R.id.activity_main_edit_text_search);
        Button searchButton = findViewById(R.id.activity_main_button_search);
        Button favoriteMoviesButton = findViewById(R.id.activity_main_button_favorite_movies);

        RecyclerView searchedMoviesRecyclerView = findViewById(R.id.activity_main_recycler_searched_movies);
        searchedMoviesRecyclerView.setHasFixedSize(true);

        searchedMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        searchedMoviesAdapter = new SearchedMoviesAdapter();
        searchedMoviesAdapter.setOnAddFavoriteMovieListener(new SearchedMoviesAdapter.OnAddFavoriteMovieListener() {
            @Override
            public void addFavoriteMovie(MovieOverview movieOverview) {
                favoriteMoviesViewModel.addFavoriteMovie(new FavoriteMovie(movieOverview.getId(), movieOverview.getTitle(), movieOverview.getPoster_path(), false));
            }
        });
        searchedMoviesRecyclerView.setAdapter(searchedMoviesAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchStr = searchEditText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayList<MovieOverview> searchedMovies = MovieApiDao.searchMovies(searchStr);

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                searchedMoviesAdapter.setSearchedMovies(searchedMovies);
                            }
                        });
                    }
                }).start();
            }
        });

        favoriteMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteMoviesActivity.class);
                startActivity(intent);

            }
        });
    }


}
