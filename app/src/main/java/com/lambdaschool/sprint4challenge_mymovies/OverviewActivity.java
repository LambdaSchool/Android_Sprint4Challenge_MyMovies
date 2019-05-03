package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OverviewActivity extends AppCompatActivity {
    TextView titleView,releaseView,overviewView;
//VERY SIMPLE OVERVIEW PAGE:TODO IS TO REMOVE ENTIRELY AND ADD AS DIALOG FRAGMENT.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Intent receiveIntent = getIntent();
        final String title = receiveIntent.getStringExtra("title");
        final String releaseDate = receiveIntent.getStringExtra("release");
        final String overview = receiveIntent.getStringExtra("overview");
        titleView = findViewById(R.id.title_view);
        releaseView = findViewById(R.id.title_release);
        overviewView = findViewById(R.id.title_overview);
        titleView.setText(title);
        releaseView.setText(releaseDate);
        overviewView.setText(overview);

    }
}
