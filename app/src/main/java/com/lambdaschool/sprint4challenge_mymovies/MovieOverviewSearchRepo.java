package com.lambdaschool.sprint4challenge_mymovies;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MovieOverviewSearchRepo {
	
	public static ArrayList<MovieOverview> movies;
	
	public static void setMovies(ArrayList<MovieOverview> setMovies){
		movies = setMovies;
	}
	
	public static MovieOverview getMovieByName(String title){
		MovieOverview movieResult = null;
		for(int i = 0; i < movies.size(); i++){
			if(movies.get(i).getTitle().equals(title)) {
				movieResult = movies.get(i);
				break;
			}
		}
		return movieResult;
	}
	
	public static ArrayList<MovieOverview> getMovies(){
		return movies;
	}
}
