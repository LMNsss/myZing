package com.ngoclm.myzing.base.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ngoclm.myzing.base.DAO.PlaylistDao
import com.ngoclm.myzing.base.db.AppDatabase
import com.ngoclm.myzing.base.entities.Playlist


class PlaylistRepository(application: Application) {
    private val playlistDao: PlaylistDao

    init {
        val playlistDatabase: AppDatabase = AppDatabase.getInstance(application)
        playlistDao = playlistDatabase.getPlayList()
    }
    suspend fun insertPlaylist(playlist: Playlist) = playlistDao.insertPlaylist(playlist)
    suspend fun updatePlayList(playlist: Playlist) = playlistDao.updatePlaylist(playlist)
    suspend fun deleteSong(playlist: Playlist) = playlistDao.deletePlaylist(playlist)

    fun getAllPlaylist(): LiveData<List<Playlist>> = playlistDao.getAllPlaylist()

}