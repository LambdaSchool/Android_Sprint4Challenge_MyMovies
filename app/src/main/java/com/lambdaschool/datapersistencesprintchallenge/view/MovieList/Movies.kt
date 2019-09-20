package com.lambdaschool.datapersistencesprintchallenge.view.MovieList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.datapersistencesprintchallenge.view.FavoriteMovieList.FavoriteMovies
import com.lambdaschool.datapersistencesprintchallenge.R
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MovieRetroFitObject
import com.lambdaschool.datapersistencesprintchallenge.room.DataBaseBuilder
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Movies : AppCompatActivity() {

    companion object {
        var listOfMovies = ArrayList<MovieOverview>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = DisplayMovieList(listOfMovies)


        search_bar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(movieName: String?): Boolean {
                MovieRetroFitObject.getListOfMovies(movieName!!).enqueue(object : Callback<MovieSearchResult> {

                    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
                        println(t)
                    }

                    override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
                        if (response.isSuccessful){
                            response.body()?.results?.forEach {
                                listOfMovies.add(it)
                            }
                            recycler_view.adapter?.notifyDataSetChanged()
                        } else {
                            println(response)
                        }
                    }
                })
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
