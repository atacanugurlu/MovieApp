package com.example.movieapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.feature.detail.DetailViewModel
import com.example.movieapp.feature.favourite.FavouriteViewModel
import com.example.movieapp.feature.list.ListViewModel
import com.example.movieapp.util.factory.ViewModelFactory
import com.example.movieapp.util.factory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    protected abstract fun listViewModel(listViewModel: ListViewModel): ViewModel

    /*

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    protected abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    protected abstract fun favouriteViewModel(favouriteViewModel: FavouriteViewModel): ViewModel

     */
}