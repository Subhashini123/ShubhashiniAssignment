package com.example.shubhashiniassignment.repo

import com.example.shubhashiniassignment.db.AlbumDbDao
import com.example.shubhashiniassignment.model.AlbumModel
import com.example.shubhashiniassignment.service.AlbumApiService
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val service: AlbumApiService,
    private val carDao: AlbumDbDao
) {
    suspend fun downloadListOfCar(): AlbumAPIResponse {
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

    suspend fun getAllCars(): List<AlbumModel.AlbumModelItem> {
        return carDao.getAllCars() ?: listOf()
    }

    suspend fun saveCarsInDb(cars: List<AlbumModel.AlbumModelItem>) {
        for (car in cars) {
            carDao.addCar(car)
        }
    }
}

// API Response sealed status
sealed class AlbumAPIResponse
data class ApiSuccess(val cars: List<AlbumModel.AlbumModelItem>?) : AlbumAPIResponse()
object ApiFailed : AlbumAPIResponse()
object SocketTimeout : AlbumAPIResponse() {
}