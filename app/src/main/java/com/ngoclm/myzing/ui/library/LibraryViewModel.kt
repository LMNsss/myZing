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
    private val contentResolver: ContentResolver = application.contentResolver
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
//        cursor?.use {
//            while (it.moveToNext()) {
//                val img = it.getString(it.getColumnIndexOrThrow("img"))
//                val songName = it.getString(it.getColumnIndexOrThrow("song_name"))
//                val singerName = it.getString(it.getColumnIndexOrThrow("singer_name"))
//                val song = Song(img,songName, singerName, false, false, "local", false, "", 0, 0, false)
//                songList.add(song)
//            }
//        }
        cursor?.use {
            val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATA)
            val titleIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)

            while (cursor.moveToNext()) {
                val filePath = cursor.getString(dataIndex)
                val title = cursor.getString(titleIndex)
                val song = Song(
                    "https://designs.vn/wp-content/images/21-09-2017/lieu-co-phai-chup-anh-duoi-ca-hai-dinh-dang-jpeg-va-raw-la-ban-mot-mui-ten-trung-hai-dich-02.jpg",
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