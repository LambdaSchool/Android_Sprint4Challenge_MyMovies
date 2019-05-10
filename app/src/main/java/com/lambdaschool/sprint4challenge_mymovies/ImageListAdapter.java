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
        sqlDAO=new FavoriteMovieSQLDAO( context ) ;

        View entryView = LayoutInflater.from(context).inflate( R.layout.image_list_view, viewGroup, false);


        return new ViewHolder(entryView);

    }



    @Override

    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        if(i>56){
            System.out.printf( "test" );
        }
        final Movie it = this.itemsList.get(i);

        this.viewHolder=viewHolder;

        viewHolder.tvName.setText(it.getMovieOverview().getTitle());
        if(it.getMovieOverview().getRelease_date().equals("")){
            viewHolder.tvYear.setText(Integer.toString( it.getiID())+"(unknown)");

        }else{
            viewHolder.tvYear.setText(Integer.toString( it.getiID())+"("+it.getMovieOverview().getRelease_date().substring( 0,4 )+")");

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
        Movie item=itemsList.findByTitle( str );


        if(item!=null){
            if(item.isbFavorite()){
             //   vh.tvName.setBackgroundColor(Color.WHITE);
               vh.parent.setBackgroundColor(Color.WHITE);
            //   vh.tvName.setBackgroundColor( Color.YELLOW);//debug purpose
            //   vh.tvName.setTextColor( Color.BLACK ); //it repeats every 14 rows somehow

            //      vh.tvName.append( item.getMovieOverview().getTitle() );//debug
                item.setbFavorite(  false );
                sqlDAO.delete(item) ;
            }else{
             //   vh.tvName.setBackgroundColor(Color.RED);
                vh.parent.setBackgroundColor(Color.RED);
             //   vh.tvName.setTextColor( Color.WHITE );//it repeats every 14 rows somehow

             //   vh.tvName.setBackgroundColor( Color.BLUE); //debug purpose

         //       vh.tvName.append( item.getStrName() );//debug

                item.setbFavorite(  true );
                sqlDAO.add(item);
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