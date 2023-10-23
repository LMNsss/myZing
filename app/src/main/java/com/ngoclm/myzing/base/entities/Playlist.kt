package com.ngoclm.myzing.base.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_table")
data class Playlist(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id_playlist") val id: Int = 0,
    @ColumnInfo(name = "img_playlist_col") val img: String,
    @ColumnInfo(name = "name_playlist_col") val playlistName: String,

)