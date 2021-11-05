package com.example.movieapp.network

import com.example.movieapp.data.movie.GetMoviesResponse
import com.example.movieapp.data.movie.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"

interface Api {

    @GET("movie/upcoming")
    fun getMovies(
        @Query("api_key") apiKey: String = "7f61a9bc205af1ae9398b674cbca110c",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

}

object MoviesRepository {

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit,
                         onError: () -> Unit) {

        api.getMovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {

                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}