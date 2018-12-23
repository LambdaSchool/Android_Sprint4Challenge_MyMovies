package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.FavoritesActivity;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button favoriteButton;
    Button searchButton;
    LinearLayout movieList;
    EditText searchText;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        favoriteButton = findViewById(R.id.favorite_button);
        searchButton = findViewById(R.id.search_button2);
        movieList = findViewById(R.id.movie_list);
        searchText = findViewById(R.id.search_text);
        context = this;

        MovieDbDao.initializeInstance(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayList<MovieOverview> movies = MovieApiDao.searchMovies(searchText.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for(int i = 0; i < movies.size(); i++){
                                    TextView textView = new TextView(context);
                                    final MovieOverview getMovies = movies.get(i);
                                    textView.setText(getMovies.getTitle() + " " + getMovies.getRelease_date());
                                    textView.setTextSize(20);
                                    textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            FavoriteMovies favoriteMovies = new FavoriteMovies(getMovies.getTitle(), getMovies.getRelease_date(), 0);
                                            MovieDbDao.createFavoriteMovie(favoriteMovies);
                                            System.out.println(getMovies.getTitle() + "has been clicked");
                                        }
                                    });
                                    movieList.addView(textView);
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
                Intent intent = new Intent(context, FavoritesActivity.class);
                startActivity(intent);
            }
        });
    }
}
