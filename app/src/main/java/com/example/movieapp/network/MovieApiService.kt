package com.example.movieapp.network


import com.example.movieapp.data.movie.MoviesResponse
import com.example.movieapp.data.movie.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepository @Inject constructor(private val api: Api){

    fun getMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {

        this.api.getPopularMovies(page = page)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {

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

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}

