package com.example.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouritesDatabaseDao {

    //Insert a movie to favourites database
    @Insert
    fun make_favourite(movie: FavouriteMovie)

    //Get a movie from favourites database
    @Query("SELECT * from favourite_movies_table WHERE id = :key")
    fun get(key: Long): FavouriteMovie

    //Delete a movie from favourites database
    @Delete
    fun delete_from_favourites(movie: FavouriteMovie)

    //Clear favourites database
    @Query("DELETE FROM favourite_movies_table")
    fun clear_favourites()

    //Get favourite movies as live data
    @Query("SELECT * FROM favourite_movies_table ORDER BY movie_title")
    fun getAllFavourites(): LiveData<List<FavouriteMovie>>
}