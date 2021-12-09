package com.example.movieapp.feature.detail


import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movieapp.data.database.FavouritesRepository
import com.example.movieapp.data.movie.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: FavouritesRepository
) : ViewModel() {

    //Get movie from database
    fun getMovieById(id: Long): Movie {
        return repository.getMovieById(id)
    }

    fun changeMovieFavor(movieId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.changeMovieFavor(movieId)
        }
    }
}


