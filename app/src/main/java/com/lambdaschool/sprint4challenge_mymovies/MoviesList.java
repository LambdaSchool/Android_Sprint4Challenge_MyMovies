package com.lambdaschool.sprint4challenge_mymovies;

import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieApiDao;
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MoviesList {
    private ArrayList<Movie> aliMovies;
    private Movie movieCurrent;

    public MoviesList() {
        this.aliMovies = new ArrayList<>( 1 );
        this.movieCurrent = null;
    }

    public MoviesList(ArrayList<Movie> aliMovies, Movie movieCurrent) {
        this.aliMovies = aliMovies;
        this.movieCurrent = movieCurrent;
    }

    public ArrayList<Movie> getAliMovies() {
        return aliMovies;
    }

    public void setAliMovies(ArrayList<Movie> aliMovies) {
        this.aliMovies = aliMovies;
        movieCurrent = aliMovies.get( size() - 1 );
    }

    public Movie getMovieCurrent() {
        return movieCurrent;
    }

    public void setMovieCurrent(Movie movieCurrent) {
        this.movieCurrent = movieCurrent;
    }


    public int size() {
        return aliMovies.size();
    }

    public Movie get(int iIndex) {
        movieCurrent = aliMovies.get( iIndex );
        return movieCurrent;
    }

    public Movie findByTitle(String strName) {

        for (int i = 0; i < size(); i++) {
            if (aliMovies.get( i ).getMovieOverview().getTitle().equals( strName )) {
                movieCurrent = aliMovies.get( i );
                return movieCurrent;
            }
        }
        return null;
    }

    public MoviesList reset() {
        for (int i = 0; i < size(); i++) {
            aliMovies.get( i ).setbWatched( false );
        }
        return this;
    }

    public MoviesList removeAll(){

            aliMovies.removeAll(  aliMovies);

        movieCurrent=null;
        return this;
    }
    private MovieApiDao movieApiDao=new MovieApiDao();

    public MoviesList getMoviesByTitle(String strName){
        ArrayList<MovieOverview> almResult;
                almResult=movieApiDao.searchMovies(strName);
        for(int i=0;i<almResult.size();i++){


            aliMovies.add( new Movie(i,almResult.get( i ),false));

        }
        return this;
    }
    public  void add(Movie movie){
        this.aliMovies.add( movie );

    }
    public  void remove(Movie movie){
        this.aliMovies.remove( movie );
    }


/*    public String getStringAllIndex(){

        String strAllIndex="";
        for(int i=0;i<size();i++){
            strAllIndex+= alitemList.get( i ).getiID();
            if(i!=size()-1)strAllIndex+=",";
        }

        return strAllIndex;
    }

    }

    public Movie findItemByName(String strName){
        for(int i=0;i<size();i++){
            if(aliMovies.get( i ).getStrName().equals( strName )){
                return alitemList.get( i );
            }

        }
        return null;
    }
    public String getListOfItemsToShop(){
        String strChosen="";
        if(size()==0)return "";
        for (int i=0;i<size();i++){
            if(alitemList.get( i ).isbToShop())strChosen+=alitemList.get( i ).getStrName()+",";
        }
        return strChosen.substring( 0,strChosen.length()-1 );
    }


    public ItemsList getChoosen(){

        ItemsList ilChoosen=null;
        for(int i=0;i<size();i++){
            if(alitemList.get(i).isbToShop()){
                if(ilChoosen==null){
                    ilChoosen=new ItemsList( alitemList.get(i) );

                }else{
                    ilChoosen.add( alitemList.get(i) );
                }
            }

        }
        return ilChoosen;
    }*/

}
