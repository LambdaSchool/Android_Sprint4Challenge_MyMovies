package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    EditText                 searchEditText;
    Button                   searchButton;
    Button                   favoritesButton;
    ArrayList<MovieOverview> currentResults;
    Context                  context;
    LinearLayout             resultsLinearLayout;
    boolean                  everyOther = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        searchEditText = findViewById(R.id.edit_text_search);
        searchButton = findViewById(R.id.button_search_movies);
        resultsLinearLayout = findViewById(R.id.linear_layout_child);
        favoritesButton = findViewById(R.id.button_favorites);
        MovieDbDao.initializeInstance(context);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        currentResults = MovieApiDao.searchMovies(searchEditText.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String temp;
                                for (MovieOverview movieOverview : currentResults) {
                                    TextView textView = new TextView(context);
                                    if (!movieOverview.getRelease_date().equals("")) {
                                        temp = movieOverview.getTitle() + " (" + movieOverview.getRelease_date().substring(0, 4) + ")";
                                    } else {
                                        temp = movieOverview.getTitle();
                                    }
                                    if (everyOther) {
                                        textView.setBackgroundColor(0x55777777);
                                    }
                                    everyOther = !everyOther; //switches the boolean
                                    textView.setOnClickListener((View.OnClickListener) context);
                                    textView.setOnLongClickListener((View.OnLongClickListener) context);
                                    textView.setTag(movieOverview.getId());
                                    textView.setText(temp);
                                    resultsLinearLayout.addView(textView);
                                }

                            }
                        });
                    }
                }).start();
            }
        });


        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FavoritesView.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        String movieId = (String) v.getTag();
        //Launch details activity
    }

    @Override
    public boolean onLongClick(View v) {
        //add movie to Db
        FavoriteMovie movie = new FavoriteMovie(((TextView) v).getText().toString());
        MovieDbDao.addMovie(movie);
        v.setBackgroundColor(0x55880000);
        return true;
    }
}
