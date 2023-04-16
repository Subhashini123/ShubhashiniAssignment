package com.example.shubhashiniassignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shubhashiniassignment.model.AlbumModel

// Offline database
@Database(entities = [AlbumModel.AlbumModelItem::class], version = 1, exportSchema = false)
abstract class AlbumRoomDatabase : RoomDatabase() {

 abstract fun albumDao() : AlbumDbDao

 companion object {
  @Volatile
  private var INSTANCE: AlbumRoomDatabase? = null

  fun getDbInstance(
   context: Context
  ): AlbumRoomDatabase {
   return INSTANCE ?: synchronized(this) {
    val instance = Room.databaseBuilder(
     context,
     AlbumRoomDatabase::class.java,
     "albumDb"
    )
     .fallbackToDestructiveMigration()
     .build()
    INSTANCE = instance
    instance
   }
  }
 }
}