package com.ngoclm.myzing.base.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_table")
data class Song(
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "song_name") val songName: String,
    @ColumnInfo(name = "singer_name") val singerName: String,
    @ColumnInfo(name = "love") val love: Boolean = false,
    @ColumnInfo(name = "recently") var recently: Boolean = false,
    @ColumnInfo(name = "play_list") val playList: String,
    @ColumnInfo(name = "downloaded") var downloaded: Boolean = false,
    @ColumnInfo(name = "file_path") val filePath: String,
    @ColumnInfo(name = "love_number") var loveNumber: Int,
    @ColumnInfo(name = "listens_number") var listensNumber: Int,
    @ColumnInfo(name = "song_last") var last: Boolean = false
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}