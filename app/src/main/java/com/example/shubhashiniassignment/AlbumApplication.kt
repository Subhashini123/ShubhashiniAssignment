package com.example.shubhashiniassignment

import android.app.Application
import com.example.shubhashiniassignment.di.AlbumApplicationComponent


class AlbumApplication : Application() {
    lateinit var appComponent : AlbumApplicationComponent
    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAlbumApplicationComponent.factory().create(applicationContext)
    }
}