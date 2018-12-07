package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    ArrayList<MovieOverview> movieList;
    Context context;

    public RecycleAdapter(ArrayList<MovieOverview> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_activity, viewGroup, false);
        context = viewGroup.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        MovieOverview overview = movieList.get(i);

        myViewHolder.movie.setText(new StringBuilder().append(overview.getTitle()).append("\n").append(overview.getOverview()).append(overview.getRelease_date()).toString());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView movie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie = itemView.findViewById(R.id.tvMovie);
        }
    }
}
