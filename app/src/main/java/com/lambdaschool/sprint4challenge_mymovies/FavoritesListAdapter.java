package com.lambdaschool.sprint4challenge_mymovies;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.ViewHolder> {

    private final MutableLiveData<ArrayList<FavoriteMovie>> movies;
    private FavoriteMovieVM viewModel;

    public FavoritesListAdapter(MutableLiveData<ArrayList<FavoriteMovie>> movies, FavoriteMovieVM viewModel) {
        this.movies = movies;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public FavoritesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favorites_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoritesListAdapter.ViewHolder viewHolder, final int position) {
        final FavoriteMovie movie = movies.getValue().get(position);

        viewHolder.movieName.setText(movie.getTitle());
        viewHolder.movieYear.setText(movie.getReleaseDate());

        viewHolder.watchedBox.setChecked(movie.isWatched());
        viewHolder.watchedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                movie.setWatched(isChecked);
                viewModel.updateMovie(position, movie);
            }
        });

        viewHolder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                viewModel.deleteMovie(movie.getId());
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parent;
        TextView movieName;
        TextView movieYear;
        CheckBox watchedBox;

        public ViewHolder(View view) {
            super(view);
            parent = view.findViewById(R.id.movie_item_parent);
            movieName = view.findViewById(R.id.favorite_movie_name);
            movieYear = view.findViewById(R.id.favorite_movie_year);
            watchedBox = view.findViewById(R.id.watched_box);
        }
    }
}
