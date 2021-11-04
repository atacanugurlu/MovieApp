package com.example.movieapp.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.movie.Movie
import com.example.movieapp.network.MoviesRepository

class ListViewModel : ViewModel() {


    private val _responseData = MutableLiveData<String>()

    val responseData: LiveData<String>
        get() = _responseData

    init {

    }


}
