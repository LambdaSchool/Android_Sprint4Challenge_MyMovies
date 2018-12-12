package com.lambdaschool.sprint4challenge_mymovies;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity implements ListViewUpdated{

    ListView listViewFavs;
    String titles[];
    boolean watchedtitles[];
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        listViewFavs = findViewById(R.id.listFavorites);
        loadFavorites();

    }

    private void loadFavorites() {

        //will fetch data
        SQLiteDatabase db = openOrCreateDatabase("app_db", MODE_PRIVATE, null);

        // cursor points to item
        Cursor cursor = db.rawQuery("SELECT * FROM FAVS", null);

        int numRows = cursor.getCount();    // returns number of rows from result
        cursor.moveToFirst();

        titles = new String[numRows];
        watchedtitles = new boolean[numRows];

        // Note: according to youtube vid, need to populate while using cursor
        for(int i = 0; i < numRows; i++) {

            String title = cursor.getString(0);
            String watched = cursor.getString(1);

            titles[i] = title;
            watchedtitles[i] = Boolean.parseBoolean(watched);

            cursor.moveToNext();

        }

        customAdapter = new CustomAdapter(FavoritesActivity.this, titles, watchedtitles);
        customAdapter.listener = this;
        listViewFavs.setAdapter(customAdapter);

        db.close();     // always close

    }




    @Override
    public void onListItemDeleted(int position) {

        String val = this.titles[position];
        ArrayList<String> newTitles = new ArrayList<>();
        ArrayList<Boolean> newWatchList = new ArrayList<>();

        for(int i = 0; i < this.titles.length; i++) {
            if(!this.titles[i].equals(val)) {
                newTitles.add(this.titles[i]);
                newWatchList.add(this.watchedtitles[i]);
            }
        }

        this.titles = (String[])newTitles.toArray(new String[newTitles.size()]);
        this.watchedtitles = new boolean[newTitles.size()];

        for (int i = 0; i < this.watchedtitles.length; i++) {
            this.watchedtitles[i] = newWatchList.get(i);
        }

        customAdapter = new CustomAdapter(this, titles, watchedtitles);
        customAdapter.listener = this;      // reset listener as new object created

        listViewFavs.setAdapter(customAdapter);

    }

    @Override
    public void onListItemUpdated(int position, String title, boolean watched) {

        this.titles[position] = title;
        this.watchedtitles[position] = watched;

        customAdapter = new CustomAdapter(this, titles, watchedtitles);
        customAdapter.listener = this;      // resets listener (same as onListItemDelete)

        listViewFavs.setAdapter(customAdapter);

    }
}
