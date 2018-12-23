package com.lambdaschool.sprint4challenge_mymovies.apiaccess;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.FavoriteMovies;
import com.lambdaschool.sprint4challenge_mymovies.MovieDbDao;
import com.lambdaschool.sprint4challenge_mymovies.R;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    private LinearLayout favorites_list;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        favorites_list = findViewById(R.id.favorites_list);
        context = this;

        final ArrayList<FavoriteMovies> favoriteMovies = MovieDbDao.listFavoriteMovies();
        for(int i = 0; i < favoriteMovies.size(); i++) {
            final TextView textView = new TextView(context);
            final FavoriteMovies getFavorites = favoriteMovies.get(i);
            textView.setText(getFavorites.getTitle()+ " " + getFavorites.getRelease_date());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getFavorites.isWatched() == 0) {
                        getFavorites.setWatched(1);
                        MovieDbDao.updateMovieWatched(getFavorites);
                        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    } else if (getFavorites.isWatched() == 1){
                        getFavorites.setWatched(0);
                        MovieDbDao.updateMovieWatched(getFavorites);
                        textView.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                }
            });
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(getFavorites.isWatched()==1)
                        MovieDbDao.deleteFavoriteMovie(getFavorites);
                        textView.setText("");
                    return false;
                }
            });
            favorites_list.addView(textView);

        }
    }
}
