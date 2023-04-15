package com.example.shubhashiniassignment.di

import android.content.Context
import com.example.shubhashiniassignment.db.AlbumDbDao
import com.example.shubhashiniassignment.db.AlbumRoomDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideAlbumDbDao(context: Context): AlbumDbDao {
        return AlbumRoomDatabase.getDbInstance(context).albumDao()
    }
}