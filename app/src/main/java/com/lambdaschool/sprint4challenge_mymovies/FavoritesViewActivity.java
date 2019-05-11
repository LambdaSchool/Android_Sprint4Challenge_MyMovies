package com.lambdaschool.sprint4challenge_mymovies;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

                final FavoriteMovieSQLDAO sqlDAO=new FavoriteMovieSQLDAO(context);
                final MoviesList moviesList =sqlDAO.getAllSQL();

                final FavoriteViewAdapter ilaAdapter=new FavoriteViewAdapter(  moviesList );
                handler.post( new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerViewChoosen = findViewById( R.id.recycler_viewFavorite );
                        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                            @Override
                            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                                return false;
                            }

                            @Override
                            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                                final int position = viewHolder.getAdapterPosition(); //swiped position

                                if (direction == ItemTouchHelper.LEFT) { //swipe left
                                    int id=moviesList.getAliMovies().get( position ).getiID();
                                    moviesList.getAliMovies().remove(position);

                                    ilaAdapter.notifyItemRemoved(position);

                                    Toast.makeText(getApplicationContext(),id +" has been deleted from this current list",Toast.LENGTH_SHORT).show();

                                }else if(direction == ItemTouchHelper.RIGHT){//swipe right
                                    int id=moviesList.getAliMovies().get( position ).getiID();
                                    sqlDAO.delete( moviesList.getAliMovies().get( position ) );
                                    moviesList.getAliMovies().remove(position);
                                    ilaAdapter.notifyItemRemoved(position);

                                    Toast.makeText(getApplicationContext(),id +" has been deleted from SQL", Toast.LENGTH_SHORT).show();

                                }

                            }
                        };
                        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
                        itemTouchHelper.attachToRecyclerView(recyclerViewChoosen  );
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

