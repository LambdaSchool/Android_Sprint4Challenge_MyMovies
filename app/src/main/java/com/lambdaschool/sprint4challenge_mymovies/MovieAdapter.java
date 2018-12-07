package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    ArrayList<MovieOverview> movieList;
    public Context context;

    public MovieAdapter(ArrayList<MovieOverview> movies, Context context) {
        this.movieList = movies;
        this.context = context;
    }


    public MovieAdapter() {
        super();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_layout, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        //        String name = movieList.get(i);                                               // not sure of this part, might need to check red lightbulb for suggestions
        String name = String.valueOf(movieList.get(i));
        viewHolder.nameView.setText(name);

    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{


        TextView nameView;

        LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            nameView = itemView.findViewById(R.id.name_view);

        }
    }

}
