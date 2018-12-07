package com.lambdaschool.sprint4challenge_mymovies.activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.lambdaschool.sprint4challenge_mymovies.R;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieDbDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
