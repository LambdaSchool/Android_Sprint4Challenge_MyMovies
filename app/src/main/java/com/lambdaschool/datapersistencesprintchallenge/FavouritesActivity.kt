package com.lambdaschool.datapersistencesprintchallenge

import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_favourites.*
import java.lang.ref.WeakReference

class FavouritesActivity : AppCompatActivity() {
    lateinit var viewModel: FavouriteMoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        viewModel = ViewModelProviders.of(this).get(FavouriteMoviesViewModel::class.java)
        ReadAllAsyncTask(this).execute()
    }

    private fun updateForEntries(favouriteMovies: List<FavouriteMovie>) {
        layout_favourite_movie_list.removeAllViews()
        favouriteMovies.forEach { favouriteMovie ->
            layout_favourite_movie_list.addView(createTextView(favouriteMovie))
        }
    }
    fun createTextView(favouritemovie:FavouriteMovie): TextView {
        val view = TextView(this)
        view.text = favouritemovie.title
        view.textSize = 24f
        view.tag=favouritemovie.id
        view.setOnClickListener {
            if(!favouritemovie.watched) {
                view.setBackgroundColor(Color.GRAY)
                var favouriteMovie = FavouriteMovie(favouritemovie.title, true, favouritemovie.id)
                UpdateAsyncTask(viewModel).execute(favouriteMovie)
            }
           else if(favouritemovie.watched) {
                view.setBackgroundColor(Color.WHITE)
                var favouriteMovie = FavouriteMovie(favouritemovie.title, false, favouritemovie.id)
                UpdateAsyncTask(viewModel).execute(favouriteMovie)
            }

        }
        return view
    }
    class UpdateAsyncTask(viewModel: FavouriteMoviesViewModel) : AsyncTask<FavouriteMovie, Void, Unit>() {
        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg favourieMovies: FavouriteMovie?) {
            if (favourieMovies.isNotEmpty()) {
                favourieMovies[0]?.let {
                    viewModel.get()?.updateFavouriteMovie(it)
                }
            }
        }
    }
    class ReadAllAsyncTask(activity: FavouritesActivity) : AsyncTask<Void, Void, LiveData<List<FavouriteMovie>>?>() {

        private val activity = WeakReference(activity)

        override fun doInBackground(vararg entries: Void?): LiveData<List<FavouriteMovie>>? {
            return activity.get()?.viewModel?.favouriteMovies
        }

        override fun onPostExecute(result: LiveData<List<FavouriteMovie>>?) {
            activity.get()?.let { act ->
                result?.let { entries ->
                    // Observe LiveData here
                    entries.observe(act,
                        Observer<List<FavouriteMovie>> { t ->
                            t?.let {
                                act.updateForEntries(t)
                            }
                        }
                    )
                }
            }
        }

    }
}
