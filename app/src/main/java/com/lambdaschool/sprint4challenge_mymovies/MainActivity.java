package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    private LinearLayout linearList;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        MovieDbDao.initializeInstance(this);
        editText = findViewById(R.id.edit_text);
        linearList= findViewById(R.id.linear_list);

        findViewById(R.id.fav_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FavoriteMoviesActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayList<MovieOverview> movies = MovieApiDao.searchMovies(editText.getText().toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                linearList.removeAllViews();
                                for(int i = 0; i < movies.size(); i++){

                                    final TextView tv = new TextView(context);

                                    final String title = movies.get(i).getTitle();

                                    final String releaseDate = movies.get(i).getRelease_date();

                                    tv.setText(title + " (" + releaseDate + ")");
                                    tv.setTextSize(20);
                                    tv.setTextColor(Color.BLACK);
                                    tv.setOnLongClickListener(new View.OnLongClickListener() {

                                        @Override
                                        public boolean onLongClick(View v) {
                                            FavoriteMovie favoriteMovie = new FavoriteMovie(title, releaseDate, 0);
                                            MovieDbDao.createFavoriteMovie(favoriteMovie);

                                            return false;
                                        }
                                    });
                                    linearList.addView(tv);
                                }
                            }
                        });
                    }
                }).start();
            }
        });


    }

}
