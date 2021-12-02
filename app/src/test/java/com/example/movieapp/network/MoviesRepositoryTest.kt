package com.example.movieapp.network

import com.example.movieapp.data.movie.MoviesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class MoviesRepositoryTest @Inject constructor(private val api: Api) {

    @Test
    fun apiTest() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getPopularMovies(page = 1)
            assert((response.code()) == 200)
        }

    }

}