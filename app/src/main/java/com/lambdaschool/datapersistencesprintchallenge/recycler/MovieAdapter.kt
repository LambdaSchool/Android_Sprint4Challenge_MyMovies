package com.lambdaschool.datapersistencesprintchallenge.recycler

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieAdapter (val data: MutableList<MovieOverview>):
        RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)

            return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val movieItems = data[position]
        holder.bindModel(movieItems)

        holder.listParent.setOnClickListener {
            if (movieItems.isVaf){
                movieItems.isVaf = false
                notifyItemChanged(position)
            }else{
                movieItems.isVaf = true
                notifyItemChanged(position)
            }
        }


    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        var id = view.movie_id_text
        var title = view.movie_title_text
        val listParent = view.movie_list
        var lastPosition = -1

        fun bindModel(list: MovieOverview){
            id.text = list.id.toString()
            title.text = list.title

            if (list.isVaf){
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
            }else
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))

        }





    }
}