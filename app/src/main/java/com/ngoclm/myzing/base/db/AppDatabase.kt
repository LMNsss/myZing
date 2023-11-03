package com.ngoclm.myzing.base.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ngoclm.myzing.base.DAO.PlaylistDao
import com.ngoclm.myzing.base.DAO.SongDao
import com.ngoclm.myzing.base.entities.Playlist
import com.ngoclm.myzing.base.entities.Song
import com.ngoclm.myzing.base.repository.PlaylistRepository

@Database(entities = [Song::class, Playlist::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSongDao(): SongDao
    abstract fun  getPlayList(): PlaylistDao
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "AppDB"
                ).allowMainThreadQueries().build()
            }
            return instance!!
        }




    }
}