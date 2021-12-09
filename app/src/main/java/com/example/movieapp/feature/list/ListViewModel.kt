package com.example.movieapp.feature.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.database.FavouritesRepository
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.network.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val repository: FavouritesRepository) :
    ViewModel() {

    fun getAllMovies(): LiveData<List<Movie>> {
        return repository.getAllMovies()
    }
    private var listedMoviesPage = 1

    fun getListedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getMovies(
                listedMoviesPage,
                ::onListedMoviesFetched,
                ::onError
            )
        }
    }

    private fun onListedMoviesFetched(movies: List<Movie>) {
        viewModelScope.launch(Dispatchers.IO) {
            for (movie in movies) {
                repository.insertMovie(movie)
            }
        }
    }

    private fun onError() {
        Log.d("List", "Failed")
    }

    fun changeMovieFavor(movieId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            repository.changeMovieFavor(movieId)
        }
    }
}
