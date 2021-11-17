package com.example.movieapp.util.network

import com.example.movieapp.data.movie.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "7f61a9bc205af1ae9398b674cbca110c",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

}