package com.lambdaschool.sprint4challenge_mymovies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.R;
import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;

import java.util.ArrayList;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder> {

    private ArrayList<FavoriteMovie> favoriteMovies = new ArrayList<>();
    private OnFavoriteMovieDeleteListener onFavoriteMovieDeleteListener;
    private OnFavoriteMovieIsWatchedChangedListener onFavoriteMovieIsWatchedChangedListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favorite_movie, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final FavoriteMovie favoriteMovie = favoriteMovies.get(i);

        viewHolder.titleTextView.setText(favoriteMovie.getTitle());
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onFavoriteMovieDeleteListener != null) {
                    onFavoriteMovieDeleteListener.onDelete(favoriteMovie);
                }
            }
        });

        viewHolder.watchedCheckbox.setChecked(favoriteMovie.isWatched());
        viewHolder.watchedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                favoriteMovie.setWatched(isChecked);
                if (onFavoriteMovieIsWatchedChangedListener != null) {
                    onFavoriteMovieIsWatchedChangedListener.onWatchedChanged(favoriteMovie);
                }
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        holder.watchedCheckbox.setOnCheckedChangeListener(null);
    }

    @Override
    public int getItemCount() {
        return favoriteMovies.size();
    }

    public void setFavoriteMovies(@NonNull ArrayList<FavoriteMovie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
        notifyDataSetChanged();
    }

    public void setOnFavoriteMovieDeleteListener(OnFavoriteMovieDeleteListener l) {
        onFavoriteMovieDeleteListener = l;
    }

    public void setOnFavoriteMovieIsWatchedChangedListener(OnFavoriteMovieIsWatchedChangedListener l) {
        onFavoriteMovieIsWatchedChangedListener = l;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.item_favorite_movie_text_title);
            deleteButton = itemView.findViewById(R.id.item_favorite_movie_button_delete);
            watchedCheckbox = itemView.findViewById(R.id.item_favorite_movie_check_box_watched);
        }

        private TextView titleTextView;
        private Button deleteButton;
        private CheckBox watchedCheckbox;
    }

    public interface OnFavoriteMovieDeleteListener {
        void onDelete(FavoriteMovie favoriteMovie);
    }

    public interface OnFavoriteMovieIsWatchedChangedListener {
        void onWatchedChanged(FavoriteMovie favoriteMovie);
    }
}
