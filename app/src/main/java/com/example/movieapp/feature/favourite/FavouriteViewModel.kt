package com.example.movieapp.feature.favourite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.database.FavouritesRepository
import com.example.movieapp.data.movie.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val repository: FavouritesRepository): ViewModel() {

    val favouriteMoviesData = MutableLiveData<List<Movie>>()
    var favouriteMoviesPage = 1

    // Get all favourite movies from the database
    fun getFavouriteMovies(): List<Movie>? {
        return repository.getAllMovies().value
    }

    // Delete a movie from the favourites database
    fun deleteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            movie.isFavourite = false
            repository.deleteMovie(movie)
            Log.i("Database", "${movie.title} deleted")
        }
    }

    // Not needed in this fragment
    // Insert a movie into the favourites database
    fun insertMovie(model: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertMovie(model)
        }
    }
}
