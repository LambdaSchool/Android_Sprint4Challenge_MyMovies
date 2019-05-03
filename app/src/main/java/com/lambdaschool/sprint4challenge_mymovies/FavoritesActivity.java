package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    ArrayList<FavoriteMovie> favoriteMovies;
    Context context;
    RecyclerView recyclerView;
    FavoritesListAdapter listAdapter;
    FavoritesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        favoriteMovies = new ArrayList<>();
        context = this;
        listAdapter = new FavoritesListAdapter(favoriteMovies);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //set observer
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        viewModel.getData().observe(this, new Observer<ArrayList<FavoriteMovie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<FavoriteMovie> liveFavorites) {
                if(liveFavorites != null){
                    favoriteMovies.clear();
                    favoriteMovies.addAll(liveFavorites);
                    listAdapter.notifyDataSetChanged();
                }
            }
        });


        //Thread to get initial list of favorites
        new Thread(new Runnable() {
            @Override
            public void run() {
                MoviesDbDAO.InitializeInstance(context);
                final ArrayList<FavoriteMovie> storedFavorites = MoviesDbDAO.getAllFavorites();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewModel.updateData(storedFavorites);
                    }
                });
            }
        }).start();

    }
}
