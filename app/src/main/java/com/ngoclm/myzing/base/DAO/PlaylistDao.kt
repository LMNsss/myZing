package com.ngoclm.myzing.base.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ngoclm.myzing.base.entities.Playlist
@Dao
interface PlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaylist(playlist: Playlist)

    @Update
    fun updatePlaylist(playlist: Playlist)

    @Delete
    fun deletePlaylist(playlist: Playlist)


    @Query("SELECT * FROM playlist_table ORDER BY id DESC")
    fun getAllPlaylist() : LiveData<List<Playlist>>
}