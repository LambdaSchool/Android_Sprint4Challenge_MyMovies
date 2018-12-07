package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieDbDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Context context;
    LinearLayout parentLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        MoviesSqlDbDao.initializeInstance(context);

        editText = findViewById(R.id.edit_search_text);
        progressBar = findViewById(R.id.progress_bar);
        parentLayout = findViewById(R.id.parent_layout);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FavoritesActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getMoviesTask().execute(editText.getText().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!editText.getText().toString().equals("")) {
            new getMoviesTask().execute(editText.getText().toString());
        }

    }

    public class getMoviesTask extends AsyncTask<String, Integer, View> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            parentLayout.removeAllViews();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
            progressBar.setVisibility(View.GONE);
            parentLayout.addView(view);
        }

        @Override
        protected View doInBackground(String... strings) {
            ArrayList<MovieOverview> movieOverviews = MovieDbDao.searchMovies(strings[0]);
            ArrayList<Integer> favoriteIds = MoviesSqlDbDao.readFavoriteIds();

            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            if (movieOverviews != null) {
                for (final MovieOverview movie : movieOverviews) {
                    final TextView tView = new TextView(context);
                    final String releaseYear = movie.getRelease_date().split("-")[0];
                    String displayText = String.format("%s (%s)", movie.getTitle(), releaseYear);
                    tView.setText(displayText);
                    tView.setTextSize(28);
                    if (favoriteIds.contains(movie.getId())) {
                        tView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    } else {
                        tView.setBackgroundColor(getResources().getColor(R.color.colorBlank));
                    }

                    tView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            ArrayList<Integer> ids = MoviesSqlDbDao.readFavoriteIds();
                            if (ids.contains(movie.getId())) {
                                tView.setBackgroundColor(getResources().getColor(R.color.colorBlank));
                                MoviesSqlDbDao.deleteMovie(movie.getId());
                            } else {
                                tView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                FavoriteMovie favoriteMovie = new FavoriteMovie(movie.getId(), movie.getTitle(), movie.getRelease_date().split("-")[0],0);
                                MoviesSqlDbDao.createMovie(favoriteMovie);
                            }
                            return false;
                        }
                    });
                    layout.addView(tView);
                }
            }

            return layout;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }
}
