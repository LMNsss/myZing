package com.ngoclm.myzing.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.repository.SongRepository
<<<<<<< HEAD
import com.ngoclm.myzing.base.singleLiveData.SingleLiveEvent
=======
import com.ngoclm.myzing.base.singleLiveData.SingleLiveData
>>>>>>> 441aad5502ac2296d61763bbddf9e801ede6436e
import com.ngoclm.myzing.ui.library.LibraryViewModel
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class MainActivityViewModel(application: Application) : ViewModel() {
    private val repository: SongRepository = SongRepository(application)
    val firstPlay = MutableLiveData<Boolean>()
    val startApp = MutableLiveData<Boolean>()
<<<<<<< HEAD
    var selectedSong = SingleLiveEvent<Song?>()
=======
    var selectedSong = SingleLiveData<Song?>()
>>>>>>> 441aad5502ac2296d61763bbddf9e801ede6436e

    fun setSelectedSong(lastSong: Song) {
        viewModelScope.launch {
            selectedSong.postValue(lastSong)
        }
    }

    init {
        firstPlay.value = true
        startApp.value = true
        selectedSong.postValue(getLastSong())
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


