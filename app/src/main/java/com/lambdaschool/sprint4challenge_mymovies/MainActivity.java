package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonSearch;
    EditText editSearch;
    ArrayList<MovieOverview> movies;
    MoviesListAdapter listAdapter;
    RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSearch = findViewById(R.id.button_search);
        editSearch = findViewById(R.id.edit_search);
        context = this;
        movies = new ArrayList<>();
        listAdapter = new MoviesListAdapter(movies);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movies.clear();
                final String searchQuery = editSearch.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayList<MovieOverview> queryResults = MovieApiDao.searchMovies(searchQuery);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                movies.addAll(queryResults);
                                listAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();


            }
        });
    }
}
