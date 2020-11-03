package com.velosobr.movieapp.view.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.velosobr.movieapp.R
import com.velosobr.movieapp.view.details.MovieDetailsActivity
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        toolbarMain.title = getString(R.string.movies_title)

        setSupportActionBar(toolbarMain)

        viewModel.moviesLiveData.observe(this) {
            it?.let { movies ->
                //with para evitar ficar chamando o recyclerMovies muitas vezes
                with(recycler_popular_movies) {
                    layoutManager =
                        LinearLayoutManager(this@MoviesActivity, RecyclerView.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = MoviesAdapter(movies) { movie ->
                        val intent =
                            MovieDetailsActivity.getStartIntent(this@MoviesActivity, movie)
                        this@MoviesActivity.startActivity(intent)
                    }
                }
            }
        }
        viewModel.getPopularMovies()

    }


}