package com.lambdaschool.sprint4challenge_mymovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;

import java.util.ArrayList;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.ViewHolder> {
    private ArrayList<FavoriteMovie> favoriteMovieArrayList;

    public FavoritesListAdapter(ArrayList<FavoriteMovie> favoriteMovieArrayList) {
        this.favoriteMovieArrayList = favoriteMovieArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_element_layout_favorites, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        FavoriteMovie favoriteMovie = favoriteMovieArrayList.get(i);

        viewHolder.imageView.setImageBitmap(MovieApiDao.getPoster(favoriteMovie.getImageSuffix()));
        viewHolder.textView.setText(String.format("%s - %s", favoriteMovie.getYear(), favoriteMovie.getTitle()));
        viewHolder.checkBoxF.setChecked(favoriteMovie.isFavorite());
        viewHolder.checkBoxW.setChecked(favoriteMovie.isWatched());
        viewHolder.viewParent.setTag(favoriteMovie.getId());
        viewHolder.viewParent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Delete favorite in SQL

                notifyItemRemoved(viewHolder.getAdapterPosition());

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteMovieArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View viewParent;
        ImageView imageView;
        TextView textView;
        CheckBox checkBoxF;
        CheckBox checkBoxW;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewParent = itemView.findViewById(R.id.recycler_view_parent_layout_favorites);
            imageView = itemView.findViewById(R.id.recycler_view_image_view_favorites);
            textView = itemView.findViewById(R.id.recycler_view_text_view_favorites);
            checkBoxF =itemView.findViewById(R.id.check_box_main_favorite_favorites);
            checkBoxW =itemView.findViewById(R.id.check_box_main_watched_favorites);
        }
    }
}
