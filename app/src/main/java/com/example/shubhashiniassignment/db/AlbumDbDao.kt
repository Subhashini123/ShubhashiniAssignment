package com.example.shubhashiniassignment.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shubhashiniassignment.model.AlbumModel

interface AlbumDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCar(user: AlbumModel.AlbumModelItem): Long

    @Query("Select * from tblAlbum")
    suspend fun getAllCars(): List<AlbumModel.AlbumModelItem>?
}