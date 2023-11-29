package com.ngoclm.myzing.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.repository.SongRepository
import com.ngoclm.myzing.base.singleLiveData.SingleLiveEvent
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class MainActivityViewModel(application: Application) : ViewModel() {
    private val repository: SongRepository = SongRepository(application)
    val firstPlay = MutableLiveData<Boolean>()
    val startApp = MutableLiveData<Boolean>()
    val _isPlaying = MutableLiveData<Boolean>()
    var selectedSong = SingleLiveEvent<Song?>()

    val isPlaying get() = _isPlaying

    init {
        firstPlay.value = true
        startApp.value = true
        selectedSong.postValue(getLastSong())
        _isPlaying.value = false
    }

    fun setPlaying(value: Boolean){
        _isPlaying.value = value
    }
    fun setSelectedSong(lastSong: Song) {
        viewModelScope.launch {
            selectedSong.postValue(lastSong)
        }
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

    fun getLastSong(): Song = repository.getLastSong(true)


    class ShareViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(application) as T
            }
            throw IllegalAccessException("Unable contruct viewmodel")
        }
    }
}


