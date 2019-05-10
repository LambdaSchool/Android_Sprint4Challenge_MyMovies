package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class FavoritesViewActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_favorites_view );
        context=getApplicationContext();
        MoviesList moviesList=new MoviesList();
        moviesList=moviesList.getMoviesByTitle("starwars");
        final ImageListAdapter ilaAdapter=new ImageListAdapter( moviesList );
        RecyclerView recyclerViewChoosen=findViewById( R.id.recycler_view_choosen);
        recyclerViewChoosen.setAdapter( ilaAdapter );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( context );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerViewChoosen.setLayoutManager( linearLayoutManager );


    }




}
