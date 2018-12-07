package com.lambdaschool.sprint4challenge_mymovies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoriteMoviesActivity extends AppCompatActivity {

    private Context context;
    private LinearLayout favesList;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);


        favesList = findViewById(R.id.fave_linear_layout);



    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<FavoriteMovie> chosenMovies = new ArrayList<>();
        chosenMovies = MovieDbDao.readFaveMovies();
        for (final FavoriteMovie temp : chosenMovies) {
            final TextView tv = new TextView(this);
            String text = temp.getTitle() +
                    " (" + temp.getStringYear()+
                    ")";
            tv.setText(text);
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MovieDbDao.deleteMovie(temp.getTitle());
                    tv.setVisibility(View.GONE);
                    return false;
                }
            });
            tv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(temp.getFavorite() == 0) {
                        temp.setFavorite(1);
                        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        temp.setFavorite(0);
                        tv.setPaintFlags(tv.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });
            favesList.addView(tv);
        }


    }
}
