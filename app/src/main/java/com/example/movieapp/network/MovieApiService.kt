package com.example.movieapp.network


import com.example.movieapp.data.movie.MoviesResponse
import com.example.movieapp.data.movie.Movie
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepository @Inject constructor(private val api: Api) {

    suspend fun getMovies(  //suspend olmalı
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getPopularMovies(page = page)

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    onSuccess.invoke(responseBody.movies)
                } else {
                    onError.invoke()
                }
            } else {
                onError.invoke()
            }
        }
    }
}




