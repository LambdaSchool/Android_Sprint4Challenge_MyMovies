package com.lambdaschool.datapersistencesprintchallenge.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview

class ListRecycler(val movieList: MutableList<MovieOverview>): RecyclerView.Adapter<ListAdapter.CustomViewHolder>(){

    lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.activity_favs, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ListAdapter.CustomViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){
        val layout: LinearLayout = vie
    }

}