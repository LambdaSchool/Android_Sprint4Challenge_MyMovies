package com.lambdaschool.datapersistencesprintchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.lambdaschool.datapersistencesprintchallenge.retrofit.MovieApi
import com.lambdaschool.sprint4challenge_mymovies.apiaccess.MovieConstants
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview
import com.lambdaschool.sprint4challenge_mymovies.model.MovieSearchResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<MovieSearchResult> {
    lateinit var movieService: MovieApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search_movie.setOnClickListener {
            movieService=MovieApi.Factory.create()
            getMovieByName(et_movie.text.toString())
        }
    }
    private fun getMovieByName(movieName: String){
        movieService.getMoviesbyName(movieName,MovieConstants.API_KEY_PARAM).enqueue(this)
    }
    override fun onResponse(call: Call<MovieSearchResult>, response: Response<MovieSearchResult>) {
        if(response.isSuccessful) {
            if (response.body() != null) {
                var movieList=response.body()?.results
                

                // layout_list.addView(createTextView(movies?,movies?.id.toString().toInt()))


            }
        }

    }


    override fun onFailure(call: Call<MovieSearchResult>, t: Throwable) {
        Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
    }
    /*fun createTextView(pokemonName: String,pokemonId:Int): TextView {
        val view = TextView(this)
        view.text = "$pokemonName-$pokemonId"
        view.textSize = 24f
        view.tag=index
        view.id=pokemonId
        view.setOnClickListener {
            val intent = Intent(this, GetPokemonActivity::class.java)
            intent.putExtra("POKEMON_ID",pokemonName)
            startActivity(intent)
        }

        return view
    }*/
}

