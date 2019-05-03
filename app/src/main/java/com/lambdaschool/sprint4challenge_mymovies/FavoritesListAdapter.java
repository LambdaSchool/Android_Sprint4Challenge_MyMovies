package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.ViewHolder>{

    private ArrayList<FavoriteMovie> dataList;

    public FavoritesListAdapter(ArrayList<FavoriteMovie> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public FavoritesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorites_item_view, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoritesListAdapter.ViewHolder viewHolder, int i) {
        final FavoriteMovie data = dataList.get(i);
        viewHolder.textTitle.setText(data.getTitle());
        if(data.isWatched()) {
            viewHolder.watched.setChecked(true);
        }

        viewHolder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MoviesDbDAO.deleteFavorite(data);
                dataList.remove(data);

                notifyDataSetChanged();

                return true;
            }
        });


        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoviesDbDAO.InitializeInstance(viewHolder.parent.getContext());
                if(!data.isWatched()) {
                    viewHolder.watched.setChecked(true);
                    data.setWatched(true);
                }else{
                    viewHolder.watched.setChecked(false);
                    data.setWatched(false);
                }
                MoviesDbDAO.updateFavorite(data);

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
        CheckBox watched;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            parent = itemView.findViewById(R.id.parent);
            watched = itemView.findViewById(R.id.checkbox_watched);
        }
    }
}