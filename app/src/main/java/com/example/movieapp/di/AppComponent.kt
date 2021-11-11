package com.example.movieapp.di

import android.app.Application
import com.example.movieapp.MainApp
import com.example.movieapp.feature.detail.DetailFragment
import com.example.movieapp.feature.favourite.FavouriteFragment
import com.example.movieapp.feature.list.ListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class
    ]
)

interface AppComponent {
    fun inject(application:Application)
    fun inject(detailFragment: DetailFragment)
    fun inject(listFragment: ListFragment)
    fun inject(favouriteFragment: FavouriteFragment)
}