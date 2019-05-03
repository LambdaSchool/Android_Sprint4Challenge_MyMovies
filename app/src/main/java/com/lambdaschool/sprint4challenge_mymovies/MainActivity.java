package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonSearch;
    Button buttonFavorites;
    EditText editSearch;
    ArrayList<MovieOverview> movies;
    MoviesListAdapter listAdapter;
    RecyclerView recyclerView;
    Context context;
    ProgressBar progressBar;
    private MoviesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSearch = findViewById(R.id.button_search);
        buttonFavorites = findViewById(R.id.button_favorites);
        editSearch = findViewById(R.id.edit_search);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        context = this;
        movies = new ArrayList<>();
        listAdapter = new MoviesListAdapter(movies);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        viewModel.getData().observe(this, new Observer<ArrayList<MovieOverview>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MovieOverview> movieOverviews) {
                if(movieOverviews != null){
                    movies.clear();
                    movies.addAll(movieOverviews);
                    listAdapter.notifyDataSetChanged();
                }
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movies.clear();
                final String searchQuery = editSearch.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayList<MovieOverview> queryResults = MovieApiDao.searchMovies(searchQuery);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewModel.updateData(queryResults);
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                }).start();


            }
        });

        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FavoritesActivity.class));
            }
        });
    }
}
