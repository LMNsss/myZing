package com.ngoclm.myzing.ui.library

import android.app.Application
import android.content.ContentResolver
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ngoclm.myzing.base.contentprovider.AppContentProvider
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.repository.SongRepository
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : ViewModel() {
    private val repository: SongRepository = SongRepository(application)
    private val contentResolver: ContentResolver= application.contentResolver
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

    //    fun getSongByRecently(recently: Boolean) = viewModelScope.launch { repository.getSongByRecently(recently) }
    fun getSongByRecently(recently: Boolean) = repository.getSongByRecently(recently)

    fun loadLocalSongs(): LiveData<List<Song>> {
        val songs = MutableLiveData<List<Song>>()
<<<<<<< HEAD
=======

        // Thực hiện truy vấn thông qua ContentProvider
        val cursor = contentResolver.query(
            AppContentProvider.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        // Xử lý dữ liệu từ Cursor và chuyển đổi thành List<Song>
        val songList = mutableListOf<Song>()
        cursor?.use {
            val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATA)
            val titleIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)

            while (cursor.moveToNext()) {
                val filePath = cursor.getString(dataIndex)
                val title = cursor.getString(titleIndex)
                val song = Song(
                    "https://cdn.sforum.vn/sforum/wp-content/uploads/2021/07/lol-t1-1.jpg",
                    title,
                    "Unknown",
                    false,
                    false,
                    "local",
                    false,
                    filePath,
                    0,
                    0,
                    false
                )
                songList.add(song)
            }
        }
        songs.value = songList
        return songs
    }
>>>>>>> 441aad5502ac2296d61763bbddf9e801ede6436e

        // Thực hiện truy vấn thông qua ContentProvider
        val cursor = contentResolver.query(
            AppContentProvider.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        // Xử lý dữ liệu từ Cursor và chuyển đổi thành List<Song>
        val songList = mutableListOf<Song>()
        cursor?.use {
            val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATA)
            val titleIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)

            while (cursor.moveToNext()) {
                val filePath = cursor.getString(dataIndex)
                val title = cursor.getString(titleIndex)
                val song = Song(
                    "https://cdn.sforum.vn/sforum/wp-content/uploads/2021/07/lol-t1-1.jpg",
                    title,
                    "Unknown",
                    false,
                    false,
                    "local",
                    false,
                    filePath,
                    0,
                    0,
                    false
                )
                songList.add(song)
            }
        }
        songs.value = songList
        return songs
    }

    fun getNumberOfLoveSong(love: Boolean): LiveData<Int> = repository.getNumberOfLoveSong(love)

    fun getNumberOfSinger(): LiveData<Int> = repository.getNumberOfSinger()
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