package com.example.shubhashiniassignment

import android.app.Application
import com.example.shubhashiniassignment.di.AlbumApplicationComponent
import com.example.shubhashiniassignment.di.DaggerAlbumApplicationComponent


class AlbumApplication : Application() {
    lateinit var appComponent: AlbumApplicationComponent
    override fun onCreate() {
        super.onCreate()
        // Initialise app component here
        appComponent = DaggerAlbumApplicationComponent.factory().create(applicationContext)
    }
}