package com.example.shubhashiniassignment.service

import com.example.shubhashiniassignment.model.AlbumModel
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApiService {
    @GET("/photos")
    suspend fun albumApi(): Response<AlbumModel?>
}