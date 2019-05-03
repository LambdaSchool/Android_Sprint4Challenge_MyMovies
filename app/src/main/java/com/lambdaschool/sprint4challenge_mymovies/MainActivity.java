package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.NetworkAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    Context context;
    
    ScrollView scrollView;
    EditText etSearchText;
    ImageView btnSearch;
    Button btnFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        
        scrollView = findViewById(R.id.sv_movie_result_list);
        etSearchText = findViewById(R.id.et_search_field);
        btnSearch = findViewById(R.id.btn_search);
        btnFavorites = findViewById(R.id.btn_favorites);
        
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        search();
                    }
                }).start();
                
            }
        });
        
        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Favorites.class);
                startActivity(intent);
            }
        });
    }
    
    public void search(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<MovieOverview> searchMovieResults = MovieApiDao.searchMovies(etSearchText.getText().toString());
                        MovieOverviewSearchRepo.setMovies(searchMovieResults);
                        
                        generateTextViews();
                    }
                });
            }
        }).start();
        
    }
    
    public void generateTextViews(){
       ArrayList<MovieOverview> movies =  MovieOverviewSearchRepo.getMovies();
       
       for(int i = 0; i < movies.size(); ++i){
           scrollView.addView(createTextView(movies.get(i).getTitle()));
       }
    }
    
    public TextView createTextView(String title){
       final TextView tv = new TextView(this);
        tv.setText(title);
        
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                MovieSqlDao.addMovie(MovieOverviewSearchRepo.getMovieByName(tv.getText().toString()));
            }
        });
        
        return tv;
    }
    
    
}
