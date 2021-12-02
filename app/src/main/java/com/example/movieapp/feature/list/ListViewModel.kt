package com.example.movieapp.feature.list

import android.util.Log
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

    val movieData = MutableLiveData<List<Movie>>()
    var listedMoviesPage = 1

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
        movieData.postValue(movies)
    }

    private fun onError() {
        Log.d("List", "Failed")
    }

    fun insertFavouriteMovie(movie: Movie){
        CoroutineScope(Dispatchers.IO).launch {
            movie.isFavourite = true
            repository.insertMovie(movie)
            Log.i("Database", "${movie.title} inserted")
        }
    }

    fun deleteFavouriteMovie(movie:Movie){
        CoroutineScope(Dispatchers.IO).launch {
            movie.isFavourite = false
            repository.deleteMovie(movie)
            Log.i("Database", "${movie.title} deleted")
        }
    }
}
