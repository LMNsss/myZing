package com.ngoclm.myzing.base.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_table")
data class Song(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "img_col") val img: String,
    @ColumnInfo(name = "song_name_col") val songName: String,
    @ColumnInfo(name = "singer_name_col") val singerName: String,
    @ColumnInfo(name = "love_col") val love: Boolean = false,
    @ColumnInfo(name = "recently_col") val recently:Boolean = false,
    @ColumnInfo(name = "play_list_col") val playList: String,
    @ColumnInfo(name = "downloaded_col") val downloaded: Boolean = false
)