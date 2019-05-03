package com.lambdaschool.sprint4challenge_mymovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
	
	ScrollView scrollView;
	LinearLayout ll;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		
		scrollView = findViewById(R.id.sv_movie_favorites_list);
		ll = findViewById(R.id.sv_ll_movie_favorites_list);
		loadFavorites();
		
	}
	
	public void loadFavorites() {
		
		MovieOverviewSearchRepo.setMoviesFromDb();
		ArrayList<MovieOverview> movies = MovieOverviewSearchRepo.getMovies();
		
		for (int i = 0; i < movies.size(); ++i) {
			ll.addView(createTextView(movies.get(i)));
		}
	}
	
	public TextView createTextView(MovieOverview movie) {
		final TextView tv = new TextView(this);
		if (movie.isWatched()) {
			tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
		}
		tv.setText(movie.getTitle());
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MovieOverview movie = MovieOverviewSearchRepo.getMovieByName(tv.getText().toString());
				
				if (movie.isWatched()) {
					movie.setIsWatched(false);
					tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
				} else {
					tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
					movie.setIsWatched(true);
				}
				MovieSqlDao.updateMovie(movie);
			}
		});
		

		
		return tv;
	}
}
