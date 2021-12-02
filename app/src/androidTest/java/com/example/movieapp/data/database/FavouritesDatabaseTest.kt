package com.example.movieapp.di

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.movieapp.data.database.FavouritesDatabase
import com.example.movieapp.data.database.FavouritesDatabaseDao
import com.example.movieapp.data.movie.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.lang.Exception

class FavouritesDatabaseTest {

    private lateinit var favouritesDatabaseDao : FavouritesDatabaseDao
    private lateinit var db: FavouritesDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, FavouritesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        favouritesDatabaseDao = db.favouritesDatabaseDao()

    }

    @After
    @Throws(IOException::class)
    fun destroyDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun makeFavouriteTest(){
        val movie = Movie(25,"title","overview","path1","path2",2.92f,"1994",false)
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDatabaseDao.makeFavourite(movie) }

        assertEquals("title",favouritesDatabaseDao.getById(25).title)

    }


}