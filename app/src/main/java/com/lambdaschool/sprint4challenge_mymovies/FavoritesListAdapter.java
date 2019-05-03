package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;

import java.util.ArrayList;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.ViewHolder> {
    private ArrayList<FavoriteMovie> favoriteMovieArrayList;
    private Context context;

    public FavoritesListAdapter(ArrayList<FavoriteMovie> favoriteMovieArrayList, Context context) {
        this.favoriteMovieArrayList = favoriteMovieArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_element_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final FavoriteMovie favoriteMovie = favoriteMovieArrayList.get(i);

        viewHolder.imageView.setImageBitmap(MovieApiDao.getSmallPoster(favoriteMovie.getImageSuffix(), context));
        viewHolder.textView.setText(String.format("%s - %s", favoriteMovie.getYear(), favoriteMovie.getTitle()));
        //viewHolder.checkBoxF.setChecked(favoriteMovie.isFavorite());
        viewHolder.checkBoxW.setChecked(favoriteMovie.isWatched());
        viewHolder.checkBoxW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                favoriteMovie.setWatched(isChecked);
                MovieDbSqlDao.updateFavorite(favoriteMovie);
            }
        });
        viewHolder.viewParent.setTag(favoriteMovie.getId());
        viewHolder.viewParent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MovieDbSqlDao.deleteFavorite(favoriteMovie.getId());
                favoriteMovieArrayList.remove(favoriteMovie);
                notifyDataSetChanged();//(viewHolder.getAdapterPosition());
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
        //CheckBox checkBoxF;
        CheckBox checkBoxW;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewParent = itemView.findViewById(R.id.recycler_view_parent_layout);
            imageView = itemView.findViewById(R.id.recycler_view_image_view);
            textView = itemView.findViewById(R.id.recycler_view_text_view);
            //checkBoxF = itemView.findViewById(R.id.check_box_main_favorite_favorites);
            checkBoxW = itemView.findViewById(R.id.check_box_main_favorite);
        }
    }
}
