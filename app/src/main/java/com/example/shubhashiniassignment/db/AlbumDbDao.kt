package com.example.shubhashiniassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shubhashiniassignment.model.AlbumModel
@Dao
interface AlbumDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhoto(user: AlbumModel.AlbumModelItem): Long

    @Query("Select * from tblAlbum")
    suspend fun getTenPhotoAlbumAtOnce(): List<AlbumModel.AlbumModelItem>?

    @Query("Select COUNT(id) From tblAlbum")
    suspend fun totalRecords(): Int

    @Query("Select * from tblAlbum LIMIT 10 OFFSET :currentIndex")
    suspend fun getAlbumInLegacyWay(currentIndex: Int): List<AlbumModel.AlbumModelItem>?
}