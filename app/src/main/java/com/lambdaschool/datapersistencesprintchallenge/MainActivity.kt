package com.lambdaschool.datapersistencesprintchallenge

import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MovieApi
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {
    var textIsGrey=false
    lateinit var viewModel: FavouriteMoviesViewModel
    lateinit var movieService: MovieApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search_movie.setOnClickListener {
            movieService=MovieApi.Factory.create()
            getMovieByName(et_movie.text.toString())
        }
        btn_view_favourites.setOnClickListener {
            val intent = Intent(this, FavouritesActivity::class.java)
            startActivity(intent)
        }
        viewModel = ViewModelProviders.of(this).get(FavouriteMoviesViewModel::class.java)
    }
    private fun getMovieByName(movieName: String){
        movieService.getMoviesbyName(movieName,MovieConstants.API_KEY_PARAM).enqueue(this)
    }
    override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
        if(response.isSuccessful) {
            if (response.body() != null) {
                var movieList=response.body()?.results
           movieList?.forEach {
             layout_list.addView(createTextView(it.title,it.id))
      }
            }
        }

    }
    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
        Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
    }
    fun createTextView(movieTitle:String,movieId:Int): TextView {
        val view = TextView(this)
        view.text = movieTitle
        view.textSize = 24f
        view.tag=movieId

        view.setOnClickListener {

                view.setBackgroundColor(Color.GRAY)
                val favouriteMovie = FavouriteMovie(movieTitle, false, movieId)
                CreateAsyncTask(viewModel).execute(favouriteMovie)


        }
        return view
    }
    class CreateAsyncTask(viewModel: FavouriteMoviesViewModel) : AsyncTask<FavouriteMovie, Void, Unit>() {
        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg favouriteMovies:FavouriteMovie?) {
            if (favouriteMovies.isNotEmpty()) {
                favouriteMovies[0]?.let {
                    viewModel.get()?.createFavouriteMovie(it)
                }
            }
        }
    }

}

