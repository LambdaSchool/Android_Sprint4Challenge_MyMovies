package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieDbDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Context context;
    LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        editText = findViewById(R.id.edit_search_text);
        parentLayout = findViewById(R.id.parent_layout);

        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new getMoviesTask().execute(editText.getText().toString());
            }
        });
    }



    public class getMoviesTask extends AsyncTask<String, Integer, View> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressBar.setVisibility(View.VISIBLE);
//            progressBar.setMax(25);
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
//            progressBar.setVisibility(View.GONE);
            parentLayout.addView(view);
        }

        @Override
        protected View doInBackground(String... strings) {
            ArrayList<MovieOverview> movieOverviews = MovieDbDao.searchMovies(strings[0]);
            LinearLayout view = new LinearLayout(context);
            view.setOrientation(LinearLayout.VERTICAL);
            if (movieOverviews != null) {
                for (int i = 0; i < movieOverviews.size(); i++) {
                    view.addView(getDefaultTextView(movieOverviews.get(i)));
//                    publishProgress(i + 5);
//                    try {
//                        Thread.sleep(250);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }

            return view;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            parentLayout.addView(values[0]);
//            progressBar.setProgress(values[0]);
        }
    }

    TextView getDefaultTextView(final MovieOverview movie) {
        TextView view = new TextView(context);
        final String releaseYear = movie.getRelease_date().split("-")[0];
        String displayText = String.format("%s (%s)", movie.getTitle(), releaseYear);
        view.setText(displayText);
        view.setTextSize(28);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int id = movie.getId();
                return false;
            }
        });
        return view;
    }



}
