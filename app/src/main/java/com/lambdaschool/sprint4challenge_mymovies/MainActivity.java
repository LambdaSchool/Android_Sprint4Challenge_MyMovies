package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.List;

// Charles Godoy
// AND1 Sprint4

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MovieResponse, AdapterView.OnItemClickListener{

    ListView listMovies;
    EditText editSearch;
    Button searchButton;
    Button favButton;


    private List<MovieOverview> moviesOverviews;


    MovieAsyncTask task;

    ArrayAdapter<String> arrayAdapter;      // array provided by Android for listview
    String[] movieOverviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listMovies = findViewById(R.id.list_movies);

        listMovies.setOnItemClickListener((AdapterView.OnItemClickListener) this);      // red light bulb said to cast this (might need to double check this if theres problems)
        searchButton = findViewById(R.id.search_button);
        favButton = findViewById(R.id.fav_button);
        editSearch = findViewById(R.id.edit_text);


        searchButton.setOnClickListener(this);
        favButton.setOnClickListener(this);

        prepareDatabase();


//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (v.getId() == searchButton.getId()) {
//                    String query = editSearch.getText().toString();
//
//                    if (query != null && query != "") {       // Note: stop forgetting to add this when doing repl, lol.
//
//                        task = new MovieAsyncTask();
//
//                        task.movieResponseListener = this;
//
//
//                        task.execute(query);
//                    }
//
//
//                }
//
//            }
//        });
//
//
//        favButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (v.getId() == favButton.getId()) {
//                    Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
//                    startActivity(intent);
//
//                }
//
//            }
//        });

    }


    private void prepareDatabase() {
        SQLiteDatabase db = openOrCreateDatabase("app_db", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS FAVS (title VARCHAR, watched boolean);");


        db.close();
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == searchButton.getId()){

            // for search button
            String query = editSearch.getText().toString();
            if(query!=null && query!=""){

                task  = new MovieAsyncTask();
                task.movieResponseListener =  this;

                task.execute(query);    // populates list

            }

            // for fav button
        }else if(view.getId() == favButton.getId()){
            Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(intent);

        }
    }




    @Override
    public void onMoviesLoaded(List<MovieOverview> movieOverviewList) {


        this.moviesOverviews = movieOverviewList;                           // data is here


        movieOverviews = new String[this.moviesOverviews.size()];                           // String array
        for (int i = 0; i < this.moviesOverviews.size(); ++i) {

            // to store title from the movieoverview object to the string array
            movieOverviews[i] = this.moviesOverviews.get(i).getTitle();
        }


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieOverviews);
        listMovies.setAdapter(arrayAdapter);        // to see things on the listview

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getApplicationContext(),String.valueOf(position), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);

        intent.putExtra("selectedMovie", this.moviesOverviews.get(position));
        startActivity(intent);

    }

//    class MovieAsyncTask extends AsyncTask<String, String, List<MovieOverview>> {
//
//        @Override
//        protected List<MovieOverview> doInBackground(String... strings) {
//            return null;
//        }
//    }
}
