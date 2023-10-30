package com.ngoclm.myzing.ui.library

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.repository.SongRepository
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : ViewModel() {
    private val repository: SongRepository = SongRepository(application)

    fun insertSong(song: Song) = viewModelScope.launch {
        repository.insertSong(song)
    }

    fun updateSong(song: Song) = viewModelScope.launch {
        repository.updateSong(song)
    }

    fun deleteSong(song: Song) = viewModelScope.launch {
        repository.deleteSong(song)
    }

    fun getAllSong(): LiveData<List<Song>> = repository.getAllSong()

    fun getSongById(id: Int): LiveData<List<Song>> = repository.getSongById(id)


    @Suppress("UNCHECKED_CAST")
    class SongViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
                return LibraryViewModel(application) as T
            }
            throw IllegalAccessException("Unable contruct viewmodel")
        }
    }

}