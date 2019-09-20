package com.lambdaschool.datapersistencesprintchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack.listOfMovies
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MovieRetroFitObject
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = DisplayMovieList(listOfMovies)

        var test = listOfMovies

        search_bar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(movieName: String?): Boolean {
                MovieRetroFitObject.getListOfMovies(movieName!!).enqueue(ListOfMoviesCallBack)
                recycler_view.adapter?.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                listOfMovies.clear()
                recycler_view.adapter?.notifyDataSetChanged()
                return true
            }


        })



        button_favorite_movies.setOnClickListener {
            startActivity(Intent(this, FavoriteMovies::class.java))
        }
    }
}
