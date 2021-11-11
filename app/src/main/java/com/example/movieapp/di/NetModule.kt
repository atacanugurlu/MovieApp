package com.example.movieapp.di

import android.app.Application
import com.example.movieapp.network.Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.movieapp.data.Constants.BASE_URL
import javax.inject.Singleton

@Module
class NetModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

}