package com.lambdaschool.sprint4challenge_mymovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {

    private ArrayList<MovieOverview> dataList;

    public MoviesListAdapter(ArrayList<MovieOverview> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movies_item_view, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final MovieOverview data = dataList.get(i);
        MoviesDbDAO.InitializeInstance(viewHolder.parent.getContext());
        FavoriteMovie favoriteMovie = MoviesDbDAO.getFavoriteByTitle(data.getTitle());
        if(favoriteMovie != null){
            if(favoriteMovie.isFavorite()) {
                viewHolder.favorite.setChecked(true);
            }else{
                viewHolder.favorite.setChecked(false);
            }
        }

        viewHolder.textTitle.setText(data.getTitle());

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteMovie doubleCheckFavorite = MoviesDbDAO.getFavoriteByTitle(data.getTitle());
                if (doubleCheckFavorite == null || !doubleCheckFavorite.isFavorite()) {
                    viewHolder.favorite.setChecked(true);
                    if(doubleCheckFavorite == null) {

                        FavoriteMovie newFavorite = new FavoriteMovie(data.getTitle(), 1, 0);
                        MoviesDbDAO.AddFavorite(newFavorite);
                    }else{
                        doubleCheckFavorite.setFavorite(true);
                        MoviesDbDAO.updateFavorite(doubleCheckFavorite);
                    }
                } else{
                    viewHolder.favorite.setChecked(false);
                    doubleCheckFavorite.setFavorite(false);
                    MoviesDbDAO.updateFavorite(doubleCheckFavorite);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        View parent;
        CheckBox favorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            parent = itemView.findViewById(R.id.parent);
            favorite = itemView.findViewById(R.id.checkbox_favorite);
        }
    }
}
