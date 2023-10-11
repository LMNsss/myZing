package com.ngoclm.myzing.base.DAO

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.ngoclm.myzing.base.entities.Song

interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Update
    suspend fun updateSong(song: Song)

    @Delete
    suspend fun deleteSong(song: Song)

    @Query("select * from SONG_TABLE")
    fun getAllSong() : LiveData<List<Song>>
}