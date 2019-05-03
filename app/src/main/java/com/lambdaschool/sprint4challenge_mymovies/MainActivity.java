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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout movieList;
    EditText movieSearch;
    Context context;
    Button favoritesButton,searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = findViewById(R.id.movies_list);
        movieSearch = findViewById(R.id.search_movies);
        context = this;
        MovieDbDao.initializeInstance(this);
        searchButton = findViewById(R.id.search_button);
        favoritesButton = findViewById(R.id.view_favorites_button);
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favIntent = new Intent(context, FavoriteMoviesActivity.class);
                startActivity(favIntent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //New thread to do search
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                      final ArrayList<MovieOverview> movies = MovieApiDao.searchMovies(movieSearch.getText().toString());

                     //figured a quick textview creation would be fastest, If I have time will swap out with recyclerView
                      runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              movieList.removeAllViews();
                              for(int i = 0;i<movies.size();i++){
                                  final TextView tv = new TextView(context);
                                  final String title = movies.get(i).getTitle();
                                  final String releaseDate = movies.get(i).getRelease_date();
                                  final String overview = movies.get(i).getOverview();
                                  tv.setText(title + " (" + releaseDate + ")");
                                  tv.setTextColor(Color.BLACK);
                                  tv.setTextSize(25);
                                   //setting a  click listener for information
                                    tv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent overviewIntent = new Intent(context, OverviewActivity.class);
                                            overviewIntent.putExtra("overview", overview);
                                            overviewIntent.putExtra("title",title);
                                            overviewIntent.putExtra("release",releaseDate);

                                            startActivity(overviewIntent);
                                        }
                                    });
                                  //setting an onLongClickListener to add to favorites
                                  tv.setOnLongClickListener(new View.OnLongClickListener() {
                                      @Override
                                      public boolean onLongClick(View v) {
                                          FavoriteMovie favoriteMovie = new FavoriteMovie(title, releaseDate, 0);
                                          MovieDbDao.createFavoriteMovie(favoriteMovie);
                                          tv.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                                          return false;
                                      }
                                  });
                                  movieList.addView(tv);

                              }
                          }
                      });
                    }
                }).start();
            }
        });

    }
}
