package com.example.movieapp.feature.detail


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(): ViewModel() {
    val args = MutableLiveData<DetailFragmentArgs>()


}