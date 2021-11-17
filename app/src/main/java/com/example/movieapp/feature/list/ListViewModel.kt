package com.example.movieapp.feature.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.network.MoviesRepository
import javax.inject.Inject

class ListViewModel  @Inject constructor (private val moviesRepository: MoviesRepository): ViewModel(){


    val movieData = MutableLiveData<List<Movie>>()
    var listedMoviesPage = 1

    fun getListedMovies() {
        moviesRepository.getMovies(
            listedMoviesPage,
            ::onListedMoviesFetched,
            ::onError
        )
    }

    private fun onListedMoviesFetched(movies: List<Movie>) {
       movieData.value = movies

    }
    private fun onError() {
        Log.d("List", "Failed")
    }
}
