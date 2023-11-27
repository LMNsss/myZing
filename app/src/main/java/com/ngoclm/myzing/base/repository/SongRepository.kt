package com.ngoclm.myzing.base.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ngoclm.myzing.base.DAO.SongDao
import com.ngoclm.myzing.base.db.AppDatabase
import com.ngoclm.myzing.base.entities.Song

class SongRepository (app: Application) {
    private val songDao: SongDao

    init {
        val songDataBase: AppDatabase = AppDatabase.getInstance(app)
        songDao = songDataBase.getSongDao()
    }
    suspend fun insertSong(song: Song) = songDao.insertSong(song)
    suspend fun updateSong(song: Song) = songDao.updateSong(song)
    suspend fun deleteSong(song: Song) = songDao.deleteSong(song)

    fun getAllSong(): LiveData<List<Song>> = songDao.getAllSong()

    fun getSongByRecently(recently: Boolean): LiveData<List<Song>> {
        return songDao.getItemByRecently(recently)
    }
    fun getLastSong(lastSong: Boolean): Song {
        return songDao.getLastSong(lastSong)
    }
    fun getNumberOfLoveSong(numberOfLoveSong: Boolean): LiveData<Int> {
        return songDao.getLoveSongNumber(numberOfLoveSong)
    }

    fun getNumberOfSinger(): LiveData<Int>{
        return songDao.getSingerNumber()
    }

}