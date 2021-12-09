package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.database.FavouritesDatabase
import com.example.movieapp.data.database.FavouritesDatabaseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule(val application: Application) {

    @Provides
    @Singleton
    fun provideDatabase(): FavouritesDatabase {
        return Room.databaseBuilder(
            application, FavouritesDatabase::class.java, "movies_database")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: FavouritesDatabase): FavouritesDatabaseDao {
        return database.favouritesDatabaseDao()
    }
}