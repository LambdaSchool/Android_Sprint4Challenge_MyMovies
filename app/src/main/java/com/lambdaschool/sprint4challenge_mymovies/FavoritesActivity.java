package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    Context context;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        context = this;
        layout = findViewById(R.id.parent);

        ArrayList<FavoriteMovie> favorites = new ArrayList<>();
        favorites = MoviesSqlDbDao.readFavorites();
        for (final FavoriteMovie favorite : favorites) {
            String lineItem = String.format("%s (%s)", favorite.getTitle(), favorite.getRelease_date());
            final TextView view = new TextView(context);
            view.setText(lineItem);
            view.setTextSize(28);
            if (favorite.getWatched() == 1) {
                view.setPaintFlags(view.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                view.setPaintFlags(view.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (favorite.getWatched() == 1) {
                        view.setPaintFlags(view.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                        favorite.setWatched(0);
                        MoviesSqlDbDao.updateMovie(favorite);
                    } else {
                        view.setPaintFlags(view.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        favorite.setWatched(1);
                        MoviesSqlDbDao.updateMovie(favorite);
                    }
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MoviesSqlDbDao.deleteMovie(favorite.getId());
                    view.setText("");
                    return false;
                }
            });
            layout.addView(view);
        }
    }
}
