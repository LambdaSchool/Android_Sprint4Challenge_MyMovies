package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;


public class MovieListAdapter extends RecyclerView.Adapter<com.lambdaschool.sprint4challenge_mymovies.MovieListAdapter.ViewHolder> {

    private final ArrayList<MovieOverview> movies;
    Context context;
    private FavoriteMovieVM viewModel;

    public MovieListAdapter(ArrayList<MovieOverview> movies, FavoriteMovieVM viewModel) {
        this.movies = movies;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public com.lambdaschool.sprint4challenge_mymovies.MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.lambdaschool.sprint4challenge_mymovies.MovieListAdapter.ViewHolder viewHolder, int position) {
        final MovieOverview movie = movies.get(position);
        viewHolder.movieName.setText(movie.getTitle());
        viewHolder.movieYear.setText(movie.getRelease_date());

        final ArrayList<Integer> ids = viewModel.getFavoriteIds().getValue();
        final boolean isFavorite = ids.contains(movie.getId());
        if (isFavorite) {
            viewHolder.parent.setBackgroundResource(R.color.colorAccent);
        } else {
            viewHolder.parent.setBackgroundResource(android.R.color.white);
        }

        viewHolder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FavoriteMovie favoriteMovie = new FavoriteMovie(movie.getId(),
                        movie.getTitle(), movie.getOverview(), movie.getRelease_date(), false);

                if (!ids.contains(movie.getId())) {
                    v.setBackgroundResource(R.color.colorAccent);
                    viewModel.addMovie(favoriteMovie);
                } else {
                    v.setBackgroundResource(android.R.color.white);
                    viewModel.deleteMovie(favoriteMovie.getId());
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView parent;
        TextView movieName;
        TextView movieYear;

        public ViewHolder(View view) {
            super(view);
            parent = view.findViewById(R.id.movie_item_parent);
            movieName = view.findViewById(R.id.movie_name);
            movieYear = view.findViewById(R.id.movie_year);
        }
    }
}
