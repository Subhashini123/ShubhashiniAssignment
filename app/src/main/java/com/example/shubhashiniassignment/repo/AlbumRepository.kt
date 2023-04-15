package com.example.shubhashiniassignment.repo

import com.example.shubhashiniassignment.db.AlbumDbDao
import com.example.shubhashiniassignment.model.AlbumModel
import com.example.shubhashiniassignment.service.AlbumApiService
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val service: AlbumApiService,
    private val dao: AlbumDbDao
) {
    suspend fun downloadListOfPhoto(): AlbumAPIResponse {
        return try {
            val call = service.albumApi()
            if (call.isSuccessful) {
                val albums = call.body()
                ApiSuccess(albums)
            } else {
                ApiFailed
            }
        } catch (sException: Exception) {
            SocketTimeout
        }
    }

    suspend fun getAllPhotos(): List<AlbumModel.AlbumModelItem> {
        return dao.getTenPhotoAlbumAtOnce() ?: listOf()
    }

    suspend fun savePhotosInDb(photoAlbums: List<AlbumModel.AlbumModelItem>) {
        for (photoAlbum in photoAlbums) {
            dao.addPhoto(photoAlbum)
        }
    }

    suspend fun getRecordsCount() : Int {
        return dao.totalRecords()
    }
}

// API Response sealed status
sealed class AlbumAPIResponse
data class ApiSuccess(val albums: List<AlbumModel.AlbumModelItem>?) : AlbumAPIResponse()
object ApiFailed : AlbumAPIResponse()
object SocketTimeout : AlbumAPIResponse()