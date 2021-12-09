package com.example.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp.data.movie.Movie

@Dao
interface FavouritesDatabaseDao {

    //Insert a movie to database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(movie: Movie)

    //Get a movie from database
    @Query("SELECT * from movies_table WHERE id = :key")
    fun getItemById(key: Long): Movie

    //Delete a movie from database
    @Delete
    suspend fun deleteItem(movie: Movie)

    //Clear database
    @Query("DELETE FROM movies_table")
    fun clearDatabase()

    //Get movies as live data
    @Query("SELECT * FROM movies_table ORDER BY id DESC")
    fun getAll(): LiveData<List<Movie>>

    //Get favourite movies as live data
    @Query("SELECT * FROM movies_table WHERE movie_is_favourite = 1")
    fun getAllFavourites(): LiveData<List<Movie>>

    //Update movie favor by id
    @Query("UPDATE movies_table SET movie_is_favourite = NOT movie_is_favourite WHERE id = :key")
    fun changeItemFavor(key: Long)
}