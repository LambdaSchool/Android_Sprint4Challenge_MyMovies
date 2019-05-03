package com.lambdaschool.sprint4challenge_mymovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);



        RecyclerView recyclerView = findViewById(R.id.recycler_view_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FavoritesListAdapter listActivityAdapter = new FavoritesListAdapter(new ArrayList<FavoriteMovie>(),this);
        recyclerView.setAdapter(listActivityAdapter);
        recyclerView.setHasFixedSize(false);
    }
}
