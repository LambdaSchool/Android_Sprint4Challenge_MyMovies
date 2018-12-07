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

public class MainActivity extends AppCompatActivity {

    Context context;
    private LinearLayout searchedListLayout;
    private Button searchButton, favoriteButton;
    private EditText searchBox;
    //private ArrayList<FavoriteMovie> favesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        searchedListLayout = findViewById(R.id.movie_searched_list);
        searchButton = findViewById(R.id.search_button);
        favoriteButton = findViewById(R.id.view_faves_button);
        searchBox = findViewById(R.id.search_bar);

        //favesList = new ArrayList<FavoriteMovie>();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        final ArrayList<MovieOverview> movieList =
                                MovieApiDao.searchMovies(searchBox.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                searchedListLayout.removeAllViews();
                                for(final MovieOverview movie : movieList) {
                                    final TextView tv = new TextView(context);
                                    tv.setText(movie.getTitle() +
                                            " (" + movie.getRelease_date()+
                                            ")");
                                    tv.setOnLongClickListener(new View.OnLongClickListener() {
                                        @Override
                                        public boolean onLongClick(View v) {

                                            FavoriteMovie chosen = new FavoriteMovie(movie.getTitle(), movie.getRelease_date(), 0);
                                            MovieDbDao.createFavoriteMovie(chosen);
                                            tv.setBackgroundColor(Color.MAGENTA);
                                            tv.setTextColor(Color.WHITE);
                                            return false;
                                        }
                                    });
                                    searchedListLayout.addView(tv);

                                }
                            }
                        });
                    }
                }).start();
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FavoriteMoviesActivity.class);
                startActivity(intent);
            }
        });

    }


}
