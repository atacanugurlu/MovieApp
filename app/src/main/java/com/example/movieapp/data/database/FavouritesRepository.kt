package com.example.movieapp.data.database

import androidx.lifecycle.LiveData
import com.example.movieapp.data.movie.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesRepository @Inject constructor(
    private val favouritesDatabaseDao: FavouritesDatabaseDao) {

    //Insert a movie to favourites database
    fun insertMovie(movie: Movie){
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDatabaseDao.addItem(movie)
        }
    }

    //Get a movie from favourites database
    fun getMovieById(id: Long): Movie {
        return favouritesDatabaseDao.getItemById(id)
    }

    //Delete a movie from favourites database
    fun deleteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDatabaseDao.deleteItem(movie)
        }
    }

    //Clear favourites database
    fun clearDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDatabaseDao.clearDatabase()
        }
    }

    //Get movies as live data
    fun getAllMovies(): LiveData<List<Movie>> {
        return favouritesDatabaseDao.getAll()
    }

    //Get favourite movies as live data
    fun getAllFavouriteMovies(): LiveData<List<Movie>> {
        return favouritesDatabaseDao.getAllFavourites()
    }

    //Change movie favor by id
    fun changeMovieFavor(id: Long){
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDatabaseDao.changeItemFavor(id)
        }
    }

    /*
    //Return movies if they include searched query
    fun getSearchedMovies(query:String):LiveData<List<Movie>>{
            return favouritesDatabaseDao.getSearched(query)


    }
    */
}