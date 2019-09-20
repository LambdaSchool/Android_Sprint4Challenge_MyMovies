package com.lambdaschool.datapersistencesprintchallenge.view.FavoriteMovieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.room.DataBaseBuilder
import com.lambdaschool.datapersistencesprintchallenge.room.FavoriteMovie
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import kotlinx.android.synthetic.main.list_item.view.*

class DisplayFavoriteMovieList(val list: ArrayList<FavoriteMovie>): RecyclerView.Adapter<DisplayFavoriteMovieList.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = list[position]

        holder.movieTitle.text = movie.movieTitle
        holder.movieRating.text = "${movie.movieRating}"

        holder.movieItem.setOnLongClickListener {
            update(movie)
        }

    }

    fun update(movie: FavoriteMovie): Boolean{
        list.remove(movie)
        DataBaseBuilder.removeToFavoriteList(movie)
        this.notifyDataSetChanged()
        return true
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val movieItem = view.cv_movie
        val movieTitle = view.tv_movie_title
        val movieRating = view.tv_movie_rating
    }
}