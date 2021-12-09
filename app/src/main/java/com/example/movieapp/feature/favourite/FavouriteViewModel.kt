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
    fun getFavouriteMovies(): LiveData<List<Movie>>{
        return repository.getAllFavouriteMovies()
    }

    fun changeMovieFavor(movieId: Long){
        CoroutineScope(Dispatchers.IO).launch {
            repository.changeMovieFavor(movieId)
        }
    }
}
