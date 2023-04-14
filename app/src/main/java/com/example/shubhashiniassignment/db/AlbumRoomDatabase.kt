package com.example.shubhashiniassignment.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

// Offline database

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