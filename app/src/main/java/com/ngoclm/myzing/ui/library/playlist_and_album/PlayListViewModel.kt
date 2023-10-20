package com.ngoclm.myzing.ui.library.playlist_and_album

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngoclm.myzing.base.entities.Playlist
import com.ngoclm.myzing.base.repository.PlaylistRepository
import kotlinx.coroutines.launch

class PlayListViewModel(application: Application) : ViewModel() {
    private val repository: PlaylistRepository = PlaylistRepository(application)

    fun insertPlayList(playlist: Playlist) = viewModelScope.launch {
        repository.insertPlaylist(playlist)
    }

    fun updatePlayList(playlist: Playlist) = viewModelScope.launch {
        repository.updatePlayList(playlist)
    }

    fun deletePlayList(playlist: Playlist) = viewModelScope.launch {
        repository.deleteSong(playlist)
    }

    fun getAllPlayList(): LiveData<List<Playlist>> = repository.getAllPlaylist()

    @Suppress("UNCHECKED_CAST")
    class PlayListViewModelFactory(private val application: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlayListViewModel::class.java)){
                return PlayListViewModel(application) as T
            }
            throw IllegalAccessException("Unable contruct viewmodel")
        }
    }


}