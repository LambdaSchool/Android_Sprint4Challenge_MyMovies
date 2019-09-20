package com.lambdaschool.datapersistencesprintchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack.favoriteListOfMovies
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import kotlinx.android.synthetic.main.list_item.view.*

class DisplayFavoriteMovieList(val list: ArrayList<MovieOverview>): RecyclerView.Adapter<DisplayFavoriteMovieList.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list[position]

        holder.movieTitle.text = movie.title
        holder.movieRating.text = "Rating: ${movie.vote_average}/10"

        holder.movieItem.setOnLongClickListener {
            update(movie)
        }

    }

    fun update(movie: MovieOverview): Boolean{
        list.remove(movie)
        this.notifyDataSetChanged()
        return true
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val movieItem = view.cv_movie
        val movieTitle = view.tv_movie_title
        val movieRating = view.tv_movie_rating
    }
}