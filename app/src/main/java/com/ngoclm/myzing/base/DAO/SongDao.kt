package com.ngoclm.myzing.base.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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


    @Query("SELECT * FROM song_table ORDER BY id")
    fun getAllSong() : LiveData<List<Song>>


    @Query("SELECT * FROM song_table WHERE recently = :recently")
    fun getItemByRecently(recently: Boolean):LiveData<List<Song>>

    @Query("SELECT * FROM SONG_TABLE WHERE song_last = :songLast")
    fun getLastSong(songLast: Boolean): Song
<<<<<<< HEAD

    @Query("SELECT COUNT(love) FROM song_table WHERE love = :loveSongNumber")
    fun getLoveSongNumber(loveSongNumber: Boolean): LiveData<Int>

    @Query("SELECT COUNT(DISTINCT singer_name) FROM song_table")
    fun getSingerNumber(): LiveData<Int>
=======
>>>>>>> 441aad5502ac2296d61763bbddf9e801ede6436e
}