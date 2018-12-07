package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.content.Intent;
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
            String lineItem = String.format("%s (%s)",favorite.getTitle(), favorite.getRelease_date());
            final TextView view = new TextView(context);
            view.setText(lineItem);
            view.setTextSize(28);
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





    public class loadFavorites extends AsyncTask<Integer, Integer, View> {
        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
            layout.addView(view);
        }

        @Override
        protected View doInBackground(Integer... integers) {
//            final XkcdComic comic = XkcdDao.getComic(integers[0]);
////            TextView view = new TextView(context);
////            view.setText(comic.getTitle());
////            view.setTextSize(28);
////            view.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent intent = new Intent(context, MainActivity.class);
////                    intent.putExtra(MainActivity.COMIC_KEY, comic.getNum());
////                    startActivity(intent);
////                }
////            });
            return null;
//            return view;
        }
    }
}
