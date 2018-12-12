package com.lambdaschool.sprint4challenge_mymovies;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

public class MovieDetailActivity extends AppCompatActivity {

    MovieOverview selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        this.selectedMovie = (MovieOverview) getIntent().getSerializableExtra("selectedMovie");

        SQLiteDatabase db = openOrCreateDatabase("app_db", MODE_PRIVATE, null);

        db.execSQL("INSERT INTO FAVS VALUES('" + selectedMovie.getTitle() + "', 'false');");

        db.close();

    }
}
