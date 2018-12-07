package com.lambdaschool.sprint4challenge_mymovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieListResponse{

    EditText editText;
    RecyclerView recyclerView;
    public static String querySearch;

    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    ArrayList<MovieOverview> movieNames;
    ArrayList<MovieOverview> filteredMovieNames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void searchButton(View view) {
        querySearch = editText.getText().toString();

        if(querySearch==null || querySearch.equals("")) {
            this.filteredMovieNames = new ArrayList<>(movieNames);
            return;
        }

    }


    @Override
    public void onMovieNamesLoaded(ArrayList<MovieOverview> movieNames) {

        if(movieNames != null) {

            this.filteredMovieNames = new ArrayList<>(movieNames);

            this.movieNames = new ArrayList<>(movieNames);

            recyclerView.setAdapter(new MovieAdapter(filteredMovieNames, getApplication()));

        }

    }
}





















//package com.lambdaschool.sprint4challenge_mymovies;
//
//import android.graphics.Bitmap;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
//import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    EditText editText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        editText = findViewById(R.id.edit_text);
//
//    }
//}
