package com.example.shubhashiniassignment.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

class AlbumModel : ArrayList<AlbumModel.AlbumModelItem>(){
@Entity(tableName = "tblAlbum")
@Parcelize
data class AlbumModelItem(

    val albumId: Int,
    @PrimaryKey
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
) :Parcelable
}