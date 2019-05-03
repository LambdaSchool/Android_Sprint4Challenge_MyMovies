package com.lambdaschool.sprint4challenge_mymovies.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.R;
import com.lambdaschool.sprint4challenge_mymovies.model.FavoriteMovie;

import java.util.ArrayList;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder> {

    private ArrayList<FavoriteMovie> favoriteMovies = new ArrayList<>();
    private OnFavoriteMovieDeleteListener onFavoriteMovieDeleteListener;

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
                onFavoriteMovieDeleteListener.onDelete(favoriteMovie);
            }
        });
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.item_favorite_movie_text_title);
            deleteButton = itemView.findViewById(R.id.item_favorite_movie_button_delete);
        }

        private TextView titleTextView;
        private Button deleteButton;
    }

    public interface OnFavoriteMovieDeleteListener {
        void onDelete(FavoriteMovie favoriteMovie);
    }
}
