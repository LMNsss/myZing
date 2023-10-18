package com.ngoclm.myzing.base.DAO

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.ngoclm.myzing.base.entities.Song

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSong(song: Song)

    @Update
    fun updateSong(song: Song)

    @Delete
    fun deleteSong(song: Song)


    @Query("SELECT * FROM song_table ORDER BY id DESC")
    fun getAllSong() : LiveData<List<Song>>
}