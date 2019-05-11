package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.lambdaschool.sprint4challenge_mymovies.SQL.FavoriteMovieSQLContract;
import com.lambdaschool.sprint4challenge_mymovies.SQL.FavoriteMovieSQLDAO;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;

public class FavoritesViewActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_favorites_view );
        new Thread( new Runnable() {

            Handler handler=new Handler();
            @Override
            public void run() {
                context=getApplicationContext();

                FavoriteMovieSQLDAO sqlDAO=new FavoriteMovieSQLDAO(context);
                MoviesList moviesList =sqlDAO.getAllSQL();

                final FavoriteViewAdapter ilaAdapter=new FavoriteViewAdapter(  moviesList );
                handler.post( new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerViewChoosen = findViewById( R.id.recycler_viewFavorite );
                        recyclerViewChoosen.setAdapter( ilaAdapter );
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( context );
                        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
                        recyclerViewChoosen.setLayoutManager( linearLayoutManager );
                    }

                });
            }

        }).start();
    }

}
