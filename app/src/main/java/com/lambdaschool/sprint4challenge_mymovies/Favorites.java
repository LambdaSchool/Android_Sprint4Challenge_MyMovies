package com.lambdaschool.sprint4challenge_mymovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {
	
	ScrollView scrollView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		
		scrollView = findViewById(R.id.sv_movie_favorites_list);
		loadFavorites();
		
	}
	
	public void loadFavorites(){
		
		ArrayList<MovieOverview> movies = MovieOverviewSearchRepo.getMovies();
		
		for(int i = 0; i < movies.size(); ++i){
			scrollView.addView(createTextView(movies.get(i)));
		}
	}
	
	public TextView createTextView(MovieOverview movie){
		final TextView tv = new TextView(this);
		if(movie.isWatched()) {
			tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
		}
		tv.setText(movie.getTitle());
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
				MovieOverviewSearchRepo.getMovieByName(tv.getText().toString()).setIsWatched(true);
			}
		});
		
		return tv;
	}
}
