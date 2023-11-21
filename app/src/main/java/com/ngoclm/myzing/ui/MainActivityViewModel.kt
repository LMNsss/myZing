package com.ngoclm.myzing.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.repository.SongRepository
import com.ngoclm.myzing.base.singleLiveData.SingleLiveData
import com.ngoclm.myzing.ui.library.LibraryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("UNCHECKED_CAST")
class MainActivityViewModel(application: Application) : ViewModel() {
    private val repository: SongRepository = SongRepository(application)
    val firstPlay = MutableLiveData<Boolean>()
    val startApp = MutableLiveData<Boolean>()
    private var selectedSong = SingleLiveData<Song>()
    fun getSelectedSong(): LiveData<Song> {
        return selectedSong
    }
    fun setSelectedSong(lastSong: Song) {
        viewModelScope.launch {
            selectedSong.postValue(lastSong)
        }
    }


    init {
        firstPlay.value = true
        startApp.value = true
    }

    fun secondPlay() {
        firstPlay.value = false
    }

    fun setStartApp(value: Boolean) {
        startApp.value = value
    }

    fun updateSong(song: Song) = viewModelScope.launch {
        repository.updateSong(song)
    }

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


