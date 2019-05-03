package com.lambdaschool.sprint4challenge_mymovies;

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

    private ArrayList<MovieOverview> movieOverviewArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieDbSqlDao.initializeInstance(this);

        final Context context = this;
        final EditText editText = findViewById(R.id.edit_text);

        Button buttonSearch = findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        movieOverviewArrayList = MovieApiDao.searchMovies(editText.getText().toString());
                    }
                };

                Thread thread = new Thread(runnable);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                populateRecyclerView(movieOverviewArrayList);
            }
        });

        Button buttonFavorites = findViewById(R.id.button_favorites);
        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FavoritesActivity.class));
            }
        });


    }

    public void populateRecyclerView(ArrayList<MovieOverview> movieOverviewArrayList) {
        ArrayList<FavoriteMovie> favoriteMovieArrayList = new ArrayList<>(movieOverviewArrayList.size());

        for (MovieOverview movieOverview : movieOverviewArrayList) {
            FavoriteMovie favoriteMovie = new FavoriteMovie();
            favoriteMovie.setId(movieOverview.getId());
            favoriteMovie.setTitle(movieOverview.getTitle());
            if (movieOverview.getRelease_date().length() >= 4)
                favoriteMovie.setYear(movieOverview.getRelease_date().substring(0, 4));
            else
                favoriteMovie.setYear("0000");
            favoriteMovie.setImageSuffix(movieOverview.getPoster_path());
            favoriteMovie.setFavorite(false);
            favoriteMovie.setWatched(false);

            favoriteMovieArrayList.add(favoriteMovie);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter listAdapter = new ListAdapter(favoriteMovieArrayList,this);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setHasFixedSize(false);
    }
}
