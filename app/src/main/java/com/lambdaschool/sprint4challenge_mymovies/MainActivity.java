package com.lambdaschool.sprint4challenge_mymovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;

public class MainActivity extends AppCompatActivity {

    Button favoriteButton;
    TextView movieList;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MovieApiDao.searchMovies()
    }
}
