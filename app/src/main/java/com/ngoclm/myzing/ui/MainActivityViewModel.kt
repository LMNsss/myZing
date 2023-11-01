package com.ngoclm.myzing.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngoclm.myzing.base.entities.Song

class MainActivityViewModel: ViewModel() {
    val selected = MutableLiveData<Song>()
    fun select(song: Song) {
        selected.postValue(song)
    }
}