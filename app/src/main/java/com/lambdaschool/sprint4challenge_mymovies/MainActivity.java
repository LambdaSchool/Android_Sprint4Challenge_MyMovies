package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    MovieListAdapter movieListAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<MovieOverview> allMovies =  new ArrayList<>();
    EditText searchText;
    Button searchButton;
    Button favoritesButton;

    public static FavoriteMovieVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        viewModel = ViewModelProviders.of(this).get(FavoriteMovieVM.class);
        viewModel.getFavoriteMovies(this);

        recyclerView = findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        movieListAdapter = new MovieListAdapter(allMovies, viewModel);
        recyclerView.setAdapter(movieListAdapter);

        searchText = findViewById(R.id.search_text);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchEntry = searchText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        allMovies.addAll(MovieApiDao.searchMovies(searchEntry));
                        int i = 0;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                movieListAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        });

    }
}