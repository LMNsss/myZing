package com.ngoclm.myzing.base.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_table")
data class Playlist(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "img_col") val img: String,
    @ColumnInfo(name = "song_name_col") val playlistName: String,

)