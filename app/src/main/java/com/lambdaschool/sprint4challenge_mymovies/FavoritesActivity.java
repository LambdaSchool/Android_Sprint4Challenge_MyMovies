package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                MoviesDbDAO.InitializeInstance(context);
                final ArrayList<FavoriteMovie> storedFavorites = MoviesDbDAO.getAllFavorites();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        favoriteMovies.addAll(storedFavorites);
                        listAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

    }
}
