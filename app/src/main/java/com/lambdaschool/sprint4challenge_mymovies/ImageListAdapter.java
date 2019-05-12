package com.lambdaschool.sprint4challenge_mymovies;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lambdaschool.sprint4challenge_mymovies.SQL.FavoriteMovieSQLDAO;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder>{

    private ViewHolder viewHolder;

    private FavoriteMovieSQLDAO sqlDAO;


    public static final int EDIT_ENTRY_REQUEST_CODE = 2;


    private Context context;
    private MoviesList itemsList;

    private MoviesList moviesListinSQL;
    public ImageListAdapter(final Context context, MoviesList itemsList) {

        this.itemsList=itemsList;

        sqlDAO=new FavoriteMovieSQLDAO(context);
        new Thread( new Runnable() {
            @Override
            public void run() {
                sqlDAO=new FavoriteMovieSQLDAO(context);
                moviesListinSQL=sqlDAO.getAllSQL();;

            }
        } ).start();
    }
    public ImageListAdapter(MoviesList itemsList) {

        this.itemsList=itemsList;


    }
    public void set(MoviesList itemsList){
        this.itemsList=itemsList;
    }



    @NonNull

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();


        View entryView = LayoutInflater.from(context).inflate( R.layout.image_list_view, viewGroup, false);


        return new ViewHolder(entryView);

    }



    @Override

    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final Movie it = this.itemsList.get(i);
        String strTitle=it.getMovieOverview().getTitle(),
                strDate=it.getMovieOverview().getRelease_date();
        this.viewHolder=viewHolder;

        viewHolder.tvName.setText(strTitle);
        if(strDate.equals("")){
            viewHolder.tvYear.setTextSize( 14 );
            viewHolder.tvYear.setText( "(unknown)");
            it.getMovieOverview().setRelease_date( "0000" );
        }else{
            viewHolder.tvYear.setTextSize(20);
            viewHolder.tvYear.setText("("+it.getMovieOverview().getRelease_date().substring( 0,4 )+")");

        }
        if(it.isbFavorite()){

        }else{
            int iYear;
            try{
                iYear=Integer.parseInt( strDate.substring( 0,4 ));
            }catch (Exception e){
                iYear=0;
            }

            Movie temp=moviesListinSQL.findByTitleAndreleaseDate( strTitle,iYear);
            if(temp==null){

            }else{
                it.setbFavorite( true );
                it.setbWatched(temp.isbWatched() );
            }
        }

        if(it.isbFavorite()){
            viewHolder.parent.setBackgroundColor( Color.RED );
        }else{
            viewHolder.parent.setBackgroundColor( Color.WHITE );
        }

       // viewHolder.ivImage.setImageDrawable( context.getResources() .getDrawable( it.getMovieOverview().getPoster_path() ));

    }

    public MoviesList getItemList(){
        return this.itemsList;
    }

    private void changeBackGroundColorAndCheckData(ViewHolder vh,int positiom){

        String str=vh.tvName.getText().toString();
        final Movie item=itemsList.findByTitle( str );


        if(item!=null){
            if(item.isbFavorite()){
               vh.parent.setBackgroundColor(Color.WHITE);

                item.setbFavorite(  false );
                new Thread( new Runnable() {
                    @Override
                    public void run() {
                        sqlDAO.delete(item) ;
                    }
                } ).start();

            }else{
                vh.parent.setBackgroundColor(Color.RED);


                item.setbFavorite(  true );
                new Thread( new Runnable() {
                    @Override
                    public void run() {
                        sqlDAO.add(item);
                    }
                } ).start();

            }
        }else {
        }


    }



    @Override

    public int getItemCount() {

        return this.itemsList.size();

    }

 //3.   Add a click listener to each board in the list

    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private CardView parent;


        private ImageView ivImage;
        private TextView tvName,tvYear;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            this.parent = itemView.findViewById( R.id.element_parent);

            this.tvName= itemView.findViewById( R.id.text_name_to_choose);
            this.tvYear= itemView.findViewById( R.id.textYear);
            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);

        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it

                changeBackGroundColorAndCheckData(this,position);
            }
        }


    }

}