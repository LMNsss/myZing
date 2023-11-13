package com.ngoclm.myzing.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.repository.SongRepository
import com.ngoclm.myzing.ui.library.LibraryViewModel
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class MainActivityViewModel(application: Application) : ViewModel() {
    private val repository: SongRepository = SongRepository(application)

    fun getLastSong() = repository.getLastSong(true)

    class ShareViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(application) as T
            }
            throw IllegalAccessException("Unable contruct viewmodel")
        }
    }
}


