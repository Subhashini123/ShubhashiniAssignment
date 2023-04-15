package com.example.shubhashiniassignment.di

import com.example.shubhashiniassignment.service.AlbumApiService
import com.example.shubhashiniassignment.service.RetrofitClient
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideAlbumApiService(): AlbumApiService {
        return RetrofitClient.apiService
    }
}