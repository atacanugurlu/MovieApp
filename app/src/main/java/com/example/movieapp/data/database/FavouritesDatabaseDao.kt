package com.example.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp.data.movie.Movie

@Dao
interface FavouritesDatabaseDao {

    //Insert a movie to favourites database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun makeFavourite(movie: Movie)

    //Get a movie from favourites database
    @Query("SELECT * from favourite_movies_table WHERE id = :key")
    fun getById(key: Long): Movie

    //Delete a movie from favourites database
    @Delete
    suspend fun deleteFromFavourites(movie: Movie)

    //Clear favourites database
    @Query("DELETE FROM favourite_movies_table")
    fun clearFavourites()

    //Get favourite movies as live data
    @Query("SELECT * FROM favourite_movies_table ORDER BY movie_title")
    fun getAllFavourites(): LiveData<List<Movie>>
}