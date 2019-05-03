package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    FavoritesListAdapter favoritesListAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<FavoriteMovie> movies;

    private FavoriteMovieVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        context = this;

        viewModel = MainActivity.viewModel;
        recyclerView = findViewById(R.id.favorites_recycler_view);

        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        favoritesListAdapter = new FavoritesListAdapter(viewModel.getFavoriteMovies(context), viewModel);
        recyclerView.setAdapter(favoritesListAdapter);

    }
}
