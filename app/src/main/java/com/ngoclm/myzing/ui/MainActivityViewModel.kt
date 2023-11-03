package com.ngoclm.myzing.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.repository.SongRepository
import com.ngoclm.myzing.ui.library.LibraryViewModel

@Suppress("UNCHECKED_CAST")
class MainActivityViewModel(application: Application): ViewModel() {
    private val repository: SongRepository = SongRepository(application)

    val selected = MutableLiveData<Song>()
    fun select(song: Song) {
        selected.postValue(song)
    }
    fun getLastSong(last: Boolean ) = repository.getLastSong(last)



    class ShareViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(application) as T
            }
            throw IllegalAccessException("Unable contruct viewmodel")
        }
    }
}