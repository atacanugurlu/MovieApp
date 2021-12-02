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
            favouritesDatabaseDao.makeFavourite(movie)
        }
    }

    //Get a movie from favourites database
    fun getMovie(id: Long): Movie {
        return favouritesDatabaseDao.getById(id)
    }

    //Delete a movie from favourites database
    fun deleteMovie(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDatabaseDao.deleteFromFavourites(movie)
        }
    }

    //Clear favourites database
    fun clearDatabase() {
        favouritesDatabaseDao.clearFavourites()
    }

    //Get favourite movies as live data
    fun getAllMovies(): LiveData<List<Movie>> {
        return favouritesDatabaseDao.getAllFavourites()
    }
}