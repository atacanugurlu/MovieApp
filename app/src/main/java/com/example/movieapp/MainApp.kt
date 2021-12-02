package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.AppComponent
import com.example.movieapp.di.DaggerAppComponent
import com.example.movieapp.di.DbModule
import com.example.movieapp.di.NetModule

class MainApp : Application() {

    companion object {
        lateinit var instance: MainApp
            private set
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder().netModule(NetModule(this)).dbModule(DbModule(this))
            .build()
        appComponent.inject(this)
    }
}